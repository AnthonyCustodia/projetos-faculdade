package com.senac.montadora.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private String secret = "7AjGR3bqSw2MHrWd1vVtYnnVE4zjTMY4eIGVXPenK7A=";

    public String gerarToken() {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create().withIssuer("");
        } catch (JWTCreationException e) {

        }
    }

}
