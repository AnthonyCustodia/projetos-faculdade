package br.com.senac.estoque.config;

import br.com.senac.estoque.jwt.JwtService;
import br.com.senac.estoque.repositorios.UsuariosRepositorio;
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
import java.util.List;

// Roda em toda requisicao: le o token do header, valida e, se estiver tudo certo,
// marca o usuario como autenticado. Sem token valido, o SecurityConfig devolve 401.
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuariosRepositorio usuariosRepositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = this.recuperarToken(request);
        String email = jwtService.validarToken(token);

        if (email != null) {
            usuariosRepositorio.findByEmail(email).ifPresent(usuario -> {
                UsernamePasswordAuthenticationToken autenticacao =
                        new UsernamePasswordAuthenticationToken(usuario, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(autenticacao);
            });
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.substring("Bearer ".length()).trim();
    }
}
