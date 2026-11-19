package br.com.senac.api.services;

import br.com.senac.api.dtos.TokenResponseDto;
import br.com.senac.api.dtos.UsuariosRequestDto;
import br.com.senac.api.entidades.Usuarios;
import br.com.senac.api.jwt.JwtService;
import br.com.senac.api.respositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Regras de negócio de usuários: cadastro (com senha criptografada) e login (gera o JWT).
 */
@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepositorio usuariosRepositorio;

    // Bean BCrypt definido em SecurityConfig.passwordEncoder().
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    /**
     * Cadastra um novo usuário. A senha é salva como hash BCrypt (ver usuariosRequestDtoParaUsuarios).
     * Obs.: devolve a entidade inteira, então o JSON de resposta inclui o hash da senha.
     * Email repetido estoura a constraint unique do banco (DataIntegrityViolationException).
     */
    public Usuarios criar(UsuariosRequestDto usuario) {
        if(usuariosRepositorio.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado!");
        }

        Usuarios usuarioPersit =
                this.usuariosRequestDtoParaUsuarios(usuario);

        return usuariosRepositorio.save(usuarioPersit);

    }

    /**
     * Login: confere email e senha e, se estiverem corretos, devolve um JWT novo.
     * Passos: 1) busca o usuário pelo email; 2) compara a senha com o hash; 3) gera o token.
     */
    public TokenResponseDto login(UsuariosRequestDto usuario) {
        // 1. Procura o usuário pelo email informado.
        Optional<Usuarios> usuariosRetorno =
                usuariosRepositorio.findByEmail(usuario.getEmail());
        if(usuariosRetorno.isPresent()) {
            Usuarios usuarioDb = usuariosRetorno.get();

            // 2. matches(senhaDigitada, hashDoBanco): o BCrypt aplica o hash na senha digitada
            //    usando o salt guardado dentro do próprio hash e compara os resultados.
            if(passwordEncoder.matches(usuario.getSenha(), usuarioDb.getSenha())) {
                // 3. Credenciais corretas: gera o JWT (subject = email) e o devolve ao cliente.
                String token = jwtService.gerarToken(usuarioDb);

                return new TokenResponseDto(token);
            }

            // Obs.: mensagens diferentes para "senha errada" e "usuário não encontrado" permitem
            // descobrir quais emails estão cadastrados. O mais seguro é uma mensagem única.
            throw new RuntimeException("Senha invalda!");
        }

        throw new RuntimeException("Usuario não encotrado!");
    }

    /**
     * Converte o DTO de entrada na entidade. A senha NUNCA é salva em texto puro:
     * passwordEncoder.encode() gera o hash BCrypt (algo como "$2a$10$...").
     */
    private Usuarios usuariosRequestDtoParaUsuarios
            (UsuariosRequestDto entrada){
        Usuarios saida = new Usuarios();
        saida.setNome(entrada.getNome());
        saida.setEmail(entrada.getEmail());
        saida.setSenha(passwordEncoder.encode(entrada.getSenha()));

        return saida;
    }
}
