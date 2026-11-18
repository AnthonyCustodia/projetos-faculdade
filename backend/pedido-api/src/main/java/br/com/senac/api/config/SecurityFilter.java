package br.com.senac.api.config;

import br.com.senac.api.entidades.Usuarios;
import br.com.senac.api.jwt.JwtService;
import br.com.senac.api.respositorios.UsuariosRepositorio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/**
 * Filtro que roda ANTES dos controllers em toda requisição HTTP e faz a autenticação via JWT.
 *
 * Ele não bloqueia nada sozinho, apenas tenta identificar o usuário pelo token:
 *   - Token válido               -> registra o usuário no SecurityContext (fica "autenticado").
 *   - Sem token / token inválido -> não registra ninguém e deixa a requisição seguir.
 * Quem decide se a rota exige autenticação é o SecurityConfig (authorizeHttpRequests).
 * Se a rota exige e ninguém foi registrado aqui, o Spring Security recusa a requisição (403).
 *
 * OncePerRequestFilter: classe base do Spring que garante uma única execução por requisição.
 * O filtro é encaixado na cadeia de segurança em SecurityConfig, com addFilterBefore(...).
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuariosRepositorio usuariosRepositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Lê o token do header "Authorization" (null se o cliente não enviou).
        String token = this.recuperarToken(request);
        // 2. Valida o token: devolve o email (subject) se válido, ou null se inválido/ausente.
        String login = jwtService.validarToken(token);

        if(login != null) {
            // 3. Token válido: busca no banco o usuário dono do token.
            Optional<Usuarios> usuario =
                    usuariosRepositorio.findByEmail(login);
            if(usuario.isEmpty()) {
                // ATENÇÃO: exceção lançada dentro de um filtro não passa pelos try/catch dos
                // controllers; o cliente recebe erro (500/403) em vez de uma mensagem tratada
                // (ex.: token de um usuário que foi apagado do banco).
                throw new RuntimeException("Usuario não encontrado!");
            }

            // 4. Cria o objeto de autenticação do Spring Security:
            //    - principal   = o próprio usuário (recuperável depois com
            //                    SecurityContextHolder.getContext().getAuthentication().getPrincipal())
            //    - credentials = null (a senha não é necessária, o token já provou a identidade)
            //    - authorities = null (sem perfis/roles; internamente vira lista vazia)
            //    O construtor de 3 parâmetros já marca a autenticação como "authenticated = true".
            UsernamePasswordAuthenticationToken autorizacao =
                    new UsernamePasswordAuthenticationToken(
                            usuario.get(),
                            null,
                            null);

            // 5. Registra a autenticação no contexto da requisição atual.
            //    A partir daqui o Spring considera a requisição autenticada.
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(autorizacao);
        }

        // 6. Sempre continua a cadeia de filtros (com ou sem usuário autenticado).
        //    Sem esta linha a requisição "para" aqui e nunca chega ao controller.
        filterChain.doFilter(request, response);
    }

    /**
     * Extrai o token do header. Formato esperado: "Authorization: Bearer eyJhbGciOi..."
     * Devolve só o token (sem o prefixo "Bearer ") ou null se o header não existir.
     */
    private String recuperarToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header == null) return null;

        // Remove o prefixo "Bearer ".
        // Obs.: replace() troca em qualquer posição e não confere se o header começa com "Bearer ".
        // Espaços sobrando (ex.: "Bearer  token") quebram a validação; um .trim() no final evitaria isso.
        return header.replace("Bearer ", "");
    }
}
