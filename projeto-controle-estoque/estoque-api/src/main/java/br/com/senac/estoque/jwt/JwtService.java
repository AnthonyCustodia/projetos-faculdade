package br.com.senac.estoque.jwt;

import br.com.senac.estoque.entidades.Usuarios;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtService {

    private static final String EMISSOR = "estoque-api";

    @Value("${estoque.jwt.segredo}")
    private String segredo;

    @Value("${estoque.jwt.expiracao-horas}")
    private long expiracaoHoras;

    public String gerarToken(Usuarios usuario) {
        Algorithm algorithm = Algorithm.HMAC256(segredo);

        return JWT.create()
                .withIssuer(EMISSOR)
                .withSubject(usuario.getEmail())
                .withExpiresAt(Instant.now().plus(expiracaoHoras, ChronoUnit.HOURS))
                .sign(algorithm);
    }

    // Devolve o e-mail do usuario se o token for valido, ou null se for invalido/expirado.
    public String validarToken(String token) {
        if (token == null || token.isBlank()) {
            return null;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);

            return JWT.require(algorithm)
                    .withIssuer(EMISSOR)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
