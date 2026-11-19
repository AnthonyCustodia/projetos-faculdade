package br.com.senac.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Configuração central do Spring Security.
 *
 * Só de adicionar spring-boot-starter-security no pom.xml, TODAS as rotas ficam protegidas
 * (com tela de login e senha gerada no console). Esta classe troca esse comportamento padrão
 * pela autenticação via JWT.
 *
 * @Component: o Spring encontra a classe e registra os métodos @Bean dela.
 * Obs.: o mais comum para classes de configuração é @Configuration (+ @EnableWebSecurity).
 * Aqui @Component funciona porque nenhum método @Bean chama outro método @Bean desta classe,
 * e o Spring Boot já ativa a segurança web sozinho.
 */
@Component
public class SecurityConfig {
    // Filtro customizado que lê e valida o JWT de cada requisição.
    @Autowired
    private SecurityFilter securityFilter;

    /**
     * Define a "cadeia de filtros de segurança": as regras aplicadas a toda requisição HTTP.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                // CSRF protege aplicações que usam cookie de sessão. Como esta API é stateless
                // e o token vai no header Authorization (não em cookie), pode ser desativado.
                .csrf(csrf -> csrf.disable())
                // STATELESS: o servidor NÃO cria sessão (HttpSession) nem guarda quem está logado.
                // Cada requisição precisa trazer o token, e é por isso que o JWT é necessário.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Regras de autorização, avaliadas na ordem em que aparecem (a primeira que casar vale).
                .authorizeHttpRequests(authorize -> authorize
                        // Rotas públicas: login e cadastro precisam funcionar SEM token
                        // (senão ninguém conseguiria obter o primeiro token).
                        .requestMatchers(HttpMethod.POST, "/usuario/login").permitAll()
                        // CORREÇÃO: o cadastro também precisa ser público. Sem esta linha,
                        // cadastrar exigia token, mas só tem token quem já é cadastrado.
                        .requestMatchers(HttpMethod.POST, "/usuario/cadastro").permitAll()
                        // CORREÇÃO: "/error" é a rota interna que o Spring usa para montar respostas
                        // de erro. Se ficasse protegida, qualquer erro (400, 500...) viraria 403 sem mensagem.
                        .requestMatchers("/error").permitAll()
                        // Qualquer outra rota exige usuário autenticado (token válido).
                        // Sem token, o Spring Security responde 403 (Forbidden) por padrão.
                        .anyRequest().authenticated()
                )
                // Coloca o SecurityFilter (JWT) antes do filtro padrão de usuário/senha, para que
                // o usuário já esteja autenticado quando as regras acima forem conferidas.
                .addFilterBefore(
                        securityFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Encoder de senhas com BCrypt (hash com "salt" aleatório).
     * - encode(senha)        -> gera o hash salvo no banco (usado no cadastro).
     * - matches(senha, hash) -> compara a senha digitada com o hash (usado no login).
     * O mesmo texto gera um hash diferente a cada encode, por isso NUNCA compare com equals().
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Expõe o AuthenticationManager do Spring como bean.
     * Obs.: neste projeto ele não é usado; o login é feito "na mão" em UsuariosService
     * (findByEmail + passwordEncoder.matches). Ele seria usado numa abordagem com UserDetailsService.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
