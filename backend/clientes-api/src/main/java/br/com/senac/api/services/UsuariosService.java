package br.com.senac.api.services;

import br.com.senac.api.dtos.UsuariosRequestDto;
import br.com.senac.api.entidades.Usuarios;
import br.com.senac.api.repositorios.UsuariosRepositorio;

import java.util.List;
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
        return usuariosRepositorio.save(usuarioPersist);
    }

    public Usuarios atualizar(Long id, UsuariosRequestDto usuario) {
        if (usuariosRepositorio.existsById(id)) {
            Usuarios usuarioPersist = this.usuariosRequestDtoParaUsuarios(usuario);
            usuarioPersist.setId(id);
            return usuariosRepositorio.save(usuarioPersist);
        }
        throw new RuntimeException("Usuario nao encontrado!");
    }

    public List<Usuarios> listarTodos() {
        return usuariosRepositorio.findAll();
    }

    public void deletar(Long id) {
        if (!usuariosRepositorio.existsById(id)) {
            throw new RuntimeException("Usuario nao encontrado!");
        }
        usuariosRepositorio.deleteById(id);
    }

    public Usuarios buscarPorEmail(Long id, String email, UsuariosRequestDto usuario) {
        return usuariosRepositorio.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario nao encontrado!"));
    }

    private Usuarios usuariosRequestDtoParaUsuarios(UsuariosRequestDto entrada) {
        Usuarios saida = new Usuarios();

        saida.setNome(entrada.getNome());
        saida.setEmail(entrada.getEmail());
        saida.setSenha(passwordEncoder.encode(entrada.getSenha()));

        return saida;
    }

}
