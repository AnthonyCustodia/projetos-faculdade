package br.com.senac.api.entidades;

import jakarta.persistence.*;

/**
 * Entidade JPA da tabela "usuarios": as pessoas que podem fazer login na API.
 */
@Entity
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // length = 100: a coluna no banco vira varchar(100) (o padrão do JPA é 255).
    @Column(nullable = false, length = 100)
    private String nome;

    // unique = true: não pode haver dois usuários com o mesmo email.
    // O email é o "login" e também o "subject" (sub) gravado no JWT.
    @Column(nullable = false, unique = true)
    private String email;

    // Guarda o HASH BCrypt da senha, nunca a senha em texto puro.
    // Obs.: sem @JsonIgnore, esse hash aparece no JSON quando a entidade é devolvida (ex.: no cadastro).
    @Column(nullable = false)
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
