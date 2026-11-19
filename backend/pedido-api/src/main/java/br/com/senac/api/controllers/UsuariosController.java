package br.com.senac.api.controllers;

import br.com.senac.api.dtos.UsuariosRequestDto;
import br.com.senac.api.services.UsuariosService;
import br.com.senac.api.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Endpoints de usuário (prefixo "/usuario"). São as rotas usadas para obter o token,
 * por isso as duas estão liberadas com permitAll() em SecurityConfig.
 */
@Controller
@RequestMapping("/usuario")
public class UsuariosController {
    @Autowired
    private UsuariosService usuariosService;

    /**
     * POST /usuario/login
     * Body:     { "email": "...", "senha": "..." }
     * Resposta: { "token": "eyJhbGciOi..." }  (ver TokenResponseDto)
     * Depois, envie o token nas outras rotas no header -> Authorization: Bearer <token>
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuariosRequestDto usuario) {
        try {
            return ResponseEntity.ok(usuariosService.login(usuario));
        } catch (RuntimeException e) {
            // Usuário não encontrado ou senha errada caem aqui -> 400 { "mensagem": "..." }.
            // Obs.: para falha de login o status mais adequado seria 401 (Unauthorized).
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.parserMensagem(e.getMessage()));
        }
    }

    /**
     * POST /usuario/cadastro
     * Body: { "nome": "...", "email": "...", "senha": "..." }
     * Cria o usuário com a senha criptografada (BCrypt) e responde 201 (Created).
     * Não devolve token: depois do cadastro é preciso fazer login.
     */
    @PostMapping("/cadastro")
    public ResponseEntity<?> criar(@RequestBody UsuariosRequestDto usuario) {
        try {
            return ResponseEntity
                    .status(201)
                    .body(usuariosService.criar(usuario));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.parserMensagem(e.getMessage()));
        }
    }
}
