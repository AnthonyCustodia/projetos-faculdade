package br.com.senac.api.dtos;

/**
 * Resposta do login. Vira o JSON: { "token": "eyJhbGciOi..." }
 * O cliente (front-end, Postman, Insomnia...) guarda esse valor e o envia nas próximas
 * requisições no header: Authorization: Bearer <token>
 */
public class TokenResponseDto {
    private String token;

    public TokenResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
