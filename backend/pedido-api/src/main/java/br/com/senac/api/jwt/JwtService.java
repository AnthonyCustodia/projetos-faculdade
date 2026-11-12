package br.com.senac.api.jwt;

import br.com.senac.api.entidades.Usuarios;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {

    private String segredo = "7AjGR3bqSw2MHrWd1vVtYnnVE4zjTMY4eIGVXPenK7A";

    public String gerarToken(Usuarios usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);

            String token = JWT.create()
                    .withIssuer("pedido-api")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(this.gerarDataExpiracao())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw e;
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);

            return JWT.require(algorithm)
                    .withIssuer("pedido-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

}
