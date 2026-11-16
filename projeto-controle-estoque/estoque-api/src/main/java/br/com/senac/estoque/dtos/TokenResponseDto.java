package br.com.senac.estoque.dtos;

// Resposta do login. O frontend guarda o token e manda em "Authorization: Bearer <token>".
public class TokenResponseDto {
    private String token;
    private String nome;
    private String email;

    public TokenResponseDto(String token, String nome, String email) {
        this.token = token;
        this.nome = nome;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
