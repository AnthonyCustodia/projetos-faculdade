package br.com.senac.api.services;

import br.com.senac.api.dtos.UsuariosRequestDto;
import br.com.senac.api.entidades.Usuarios;
import br.com.senac.api.repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepositorio usuariosRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuarios criar(UsuariosRequestDto usuario) {

        Usuarios usuarioPersist = this.usuariosRequestDtoParaUsuarios(usuario);

    }

    private Usuarios usuariosRequestDtoParaUsuarios(UsuariosRequestDto entrada) {
        Usuarios saida = new Usuarios();

        saida.setNome(entrada.getNome());
        saida.setEmail(entrada.getEmail());
        saida.setSenha(passwordEncoder.encode(entrada.getSenha()));

        return saida;
    }

}
