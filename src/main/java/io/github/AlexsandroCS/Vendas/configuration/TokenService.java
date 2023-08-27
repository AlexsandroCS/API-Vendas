package io.github.AlexsandroCS.Vendas.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.github.AlexsandroCS.Vendas.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value(value = "${api.security.token.secret}")
    private String secret;

    // Gerando o token.
    public String generateToken(Usuario user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("log-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(generationExpireTime())
                    .sign(algorithm);
            return token;
        }
        catch (JWTCreationException error){
            throw new RuntimeException("Erro na geração de token!", error);
        }
    }

    private Instant generationExpireTime(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

    // Validando o token.
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("log-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException error){
            return "";
        }
    }
}
