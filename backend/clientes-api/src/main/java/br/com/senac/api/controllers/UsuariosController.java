package br.com.senac.api.controllers;

import br.com.senac.api.dtos.UsuariosRequestDto;
import br.com.senac.api.entidades.Usuarios;
import br.com.senac.api.services.UsuariosService;
import br.com.senac.api.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/listar")
    public ResponseEntity<List<Usuarios>> listarTodos() {
        return ResponseEntity.ok(usuariosService.listarTodos());
    }

    @PostMapping("/criar")
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

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody UsuariosRequestDto usuario
    ) {
        try {
            return ResponseEntity.ok(usuariosService.atualizar(id, usuario));
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
            usuariosService.deletar(id);
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
