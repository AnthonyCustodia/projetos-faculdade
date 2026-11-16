package br.com.senac.estoque.services;

import br.com.senac.estoque.dtos.TokenResponseDto;
import br.com.senac.estoque.dtos.UsuariosRequestDto;
import br.com.senac.estoque.dtos.UsuariosResponseDto;
import br.com.senac.estoque.entidades.Usuarios;
import br.com.senac.estoque.excecoes.RegraNegocioException;
import br.com.senac.estoque.jwt.JwtService;
import br.com.senac.estoque.repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepositorio usuariosRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public UsuariosResponseDto criar(UsuariosRequestDto entrada) {
        if (vazio(entrada.getNome()) || vazio(entrada.getEmail()) || vazio(entrada.getSenha())) {
            throw new RegraNegocioException("Nome, e-mail e senha sao obrigatorios!");
        }
        String email = entrada.getEmail().trim().toLowerCase();
        if (usuariosRepositorio.existsByEmail(email)) {
            throw new RegraNegocioException("Ja existe um usuario com este e-mail!");
        }

        Usuarios usuario = new Usuarios();
        usuario.setNome(entrada.getNome().trim());
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(entrada.getSenha()));

        return new UsuariosResponseDto(usuariosRepositorio.save(usuario));
    }

    public TokenResponseDto login(UsuariosRequestDto entrada) {
        if (vazio(entrada.getEmail()) || vazio(entrada.getSenha())) {
            throw new RegraNegocioException("Informe o e-mail e a senha!");
        }

        // Mesma mensagem para e-mail inexistente e senha errada: nao revela quais e-mails existem.
        Usuarios usuario = usuariosRepositorio
                .findByEmail(entrada.getEmail().trim().toLowerCase())
                .filter(u -> passwordEncoder.matches(entrada.getSenha(), u.getSenha()))
                .orElseThrow(() -> new RegraNegocioException("E-mail ou senha invalidos!"));

        return new TokenResponseDto(jwtService.gerarToken(usuario), usuario.getNome(), usuario.getEmail());
    }

    private boolean vazio(String valor) {
        return valor == null || valor.isBlank();
    }
}
