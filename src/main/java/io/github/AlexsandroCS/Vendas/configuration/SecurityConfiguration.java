package io.github.AlexsandroCS.Vendas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // Corrente de filtros de segurança que será aplicado na requisição para fazer a segurança da aplicação.
    // Fazer validações para liberar a requisição do usuário.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Requisições HTTP que serão autenticadas.
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/login","/api/registro").permitAll()

                        .requestMatchers(HttpMethod.POST,"/api/clientes","/api/pedidos","/api/produtos")
                        .hasRole("ADMIN_ROLE") // Para fazer um POST nas URL's acima, precisa ser um ADMIN.

                        .requestMatchers(HttpMethod.GET, "/api/clientes","/api/pedidos")
                        .hasRole("ADMIN_ROLE") // Para fazer um GET nas URL's acima, precisa ser um ADMIN.

                        .requestMatchers(HttpMethod.DELETE, "/api/clientes","/api/pedidos","/api/produtos")
                        .hasRole("ADMIN_ROLE") // Para fazer um DELETE nas URL's acima, precisa ser um ADMIN.

                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
