package br.com.senac.api.jwt;

import br.com.senac.api.entidades.Usuarios;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Serviço responsável por GERAR e VALIDAR tokens JWT (JSON Web Token).
 * Usa a biblioteca com.auth0:java-jwt (declarada no pom.xml).
 *
 * Lembrete: um JWT tem 3 partes separadas por ponto -> HEADER.PAYLOAD.ASSINATURA
 *   - HEADER:     algoritmo da assinatura (aqui HS256) e o tipo ("JWT").
 *   - PAYLOAD:    as "claims" (informações): iss (emissor), sub (assunto/usuário), exp (expiração)...
 *   - ASSINATURA: HMAC-SHA256(header + payload, segredo). Garante que o token não foi alterado.
 *
 * Header e payload são apenas Base64URL: QUALQUER pessoa consegue ler o conteúdo
 * (teste colando um token em https://jwt.io). Por isso nunca coloque senha ou dado sensível no token.
 * O que impede a falsificação é a assinatura: sem o segredo, ninguém gera uma assinatura válida.
 *
 * Fluxo na API:
 *   1. POST /usuario/login -> UsuariosService confere a senha e chama gerarToken().
 *   2. O cliente guarda o token e o envia em toda requisição: "Authorization: Bearer <token>".
 *   3. SecurityFilter chama validarToken() e, se válido, marca o usuário como autenticado.
 */
@Service
public class JwtService {
    // Chave secreta usada para assinar e verificar os tokens. HMAC é simétrico:
    // a MESMA chave assina e valida, então quem tiver essa chave consegue criar tokens válidos.
    // ATENÇÃO: "teste" é fácil de adivinhar (serve só para estudo). Para HS256 o recomendado é
    // um segredo aleatório com pelo menos 32 caracteres, fora do código-fonte (variável de ambiente
    // ou application.yaml, ex.: @Value("${api.security.token.secret}")).
    private String segredo = "teste";

    /**
     * Gera um token JWT assinado para o usuário informado.
     * Chamado somente depois que o login (email + senha) foi validado em UsuariosService.
     */
    public String gerarToken(Usuarios usuario) {
        try {
            // Algoritmo de assinatura: HMAC com SHA-256 usando o segredo acima.
            Algorithm algorithm = Algorithm.HMAC256(segredo);

            String token = JWT.create()
                    // "iss" (issuer): quem emitiu o token. Tem que ser IGUAL ao exigido em validarToken().
                    .withIssuer("pedidos-api")
                    // "sub" (subject): o dono do token. Aqui é o email, que o SecurityFilter
                    // usa depois para buscar o usuário no banco.
                    .withSubject(usuario.getEmail())
                    // "exp" (expiration): depois desse instante o token passa a ser recusado.
                    .withExpiresAt(this.gerarDataExpiracao())
                    // Monta header + payload, calcula a assinatura e devolve "xxxxx.yyyyy.zzzzz".
                    .sign(algorithm);

            return token;

        } catch (JWTCreationException e) {
            // Erro ao montar/assinar o token. O catch só relança a exceção (não muda nada).
            throw e;
        }
    }

    /**
     * Valida o token recebido e, se estiver tudo certo, devolve o "subject" (o email do usuário).
     * Devolve null quando o token é inválido: assinatura errada, expirado, emissor diferente,
     * formato quebrado ou token nulo. O SecurityFilter entende null como "não autenticado".
     */
    public String validarToken(String token) {
        try {
            // Tem que ser o MESMO algoritmo e o MESMO segredo usados na geração.
            Algorithm algorithm = Algorithm.HMAC256(segredo);

            return JWT.require(algorithm)
                    // Exige que o "iss" do token seja "pedidos-api" (o mesmo usado em gerarToken()).
                    .withIssuer("pedidos-api")
                    .build()
                    // verify() confere formato, assinatura, issuer e expiração (exp).
                    // Se algo falhar, lança uma subclasse de JWTVerificationException
                    // (TokenExpiredException, SignatureVerificationException, JWTDecodeException...).
                    .verify(token)
                    // Token válido: devolve o "sub" gravado em gerarToken() (o email).
                    .getSubject();

        } catch (JWTVerificationException e) {
            // Qualquer falha de validação vira null (a requisição segue sem autenticação).
            return null;
        }
    }

    /**
     * Calcula o instante de expiração do token: agora + 1 hora.
     * ATENÇÃO: LocalDateTime.now() usa o fuso do computador/servidor, mas a conversão assume
     * -03:00 (Brasília) fixo. Num servidor em outro fuso (ex.: UTC em nuvem/Docker) a validade
     * fica errada (em UTC o token duraria 4h). Alternativa sem fuso: Instant.now().plus(1, ChronoUnit.HOURS).
     */
    private Instant gerarDataExpiracao() {
        return LocalDateTime
                .now() // pega a data e hora atual
                .plusHours(1) // soma uma a data atual
                .toInstant(ZoneOffset.of("-03:00")); // define o fusohorario
    }

}
