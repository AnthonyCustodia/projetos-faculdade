package br.com.senac.api.dtos;

/**
 * Dados recebidos no corpo (JSON) das rotas /usuario/cadastro e /usuario/login.
 * - cadastro: usa nome, email e senha.
 * - login:    usa só email e senha (nome é ignorado).
 * A senha chega em texto puro e é transformada em hash no UsuariosService
 * (por isso a API deve rodar com HTTPS em produção).
 */
public class UsuariosRequestDto {
    private String nome;
    private String email;
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
