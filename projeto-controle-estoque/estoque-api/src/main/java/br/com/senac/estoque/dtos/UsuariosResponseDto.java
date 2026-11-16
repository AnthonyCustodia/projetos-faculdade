package br.com.senac.estoque.dtos;

import br.com.senac.estoque.entidades.Usuarios;

// Resposta do cadastro: tudo do usuario, menos a senha.
public class UsuariosResponseDto {
    private Long id;
    private String nome;
    private String email;

    public UsuariosResponseDto(Usuarios usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
