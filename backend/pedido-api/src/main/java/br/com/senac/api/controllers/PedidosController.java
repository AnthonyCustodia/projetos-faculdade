package br.com.senac.api.controllers;

import br.com.senac.api.dtos.PedidosRequestDto;
import br.com.senac.api.services.PedidosService;
import br.com.senac.api.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints de pedidos. TODAS as rotas daqui são PROTEGIDAS (anyRequest().authenticated() em
 * SecurityConfig): sem o header "Authorization: Bearer <token>" válido a resposta é 403.
 *
 * Obs.: usa @Controller (e não @RestController), mas funciona porque todos os métodos devolvem
 * ResponseEntity, que o Spring já converte em JSON no corpo da resposta.
 *
 * Dica: dentro de qualquer método protegido dá para saber QUEM fez a requisição com
 * (Usuarios) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
 * (é o usuário registrado pelo SecurityFilter a partir do token).
 */
@Controller
@RequestMapping("/pedidos")
public class PedidosController {
    @Autowired
    private PedidosService pedidosService;

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listarTodos() {
        return ResponseEntity.ok(pedidosService.listar());
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody PedidosRequestDto pedido) {
        try {
            return ResponseEntity
                    .status(201)
                    .body(pedidosService.criar(pedido));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.parserMensagem(e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody PedidosRequestDto pedido
    ) {
        try {
            return ResponseEntity.ok(pedidosService.atualizar(
                    id,pedido
            ));
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

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            pedidosService.deletar(id);
            return ResponseEntity.ok(null);
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
