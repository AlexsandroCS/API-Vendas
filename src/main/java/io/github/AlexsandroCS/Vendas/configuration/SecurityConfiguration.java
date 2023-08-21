package io.github.AlexsandroCS.Vendas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    public PasswordEncoder passwordEncoder(){
//        PasswordEncoder passwordEncoder = new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) { // Criptografia da senha do usuário.
//                return rawPassword + "321";
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) { // Recebe Senha do usuário e a Senha do usuário criptografada, podendo fazer uma verificação nas senhas.
//                return (rawPassword + "321").equals(encodedPassword);
//            }
//        }
        return new BCryptPasswordEncoder(); // Ao passar a senha, gera uma senha com Hash.
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder().username("root").password(passwordEncoder.encode("123")).roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }
}