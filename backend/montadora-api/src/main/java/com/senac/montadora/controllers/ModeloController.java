package com.senac.montadora.controllers;

import com.senac.montadora.dtos.ModeloRequestDto;
import com.senac.montadora.services.ModeloService;
import com.senac.montadora.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/modelo")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarModelos() {
        return ResponseEntity.ok(modeloService.listarModelos());
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody ModeloRequestDto modelo) {
        try {
            return ResponseEntity.status(201).body(modeloService.criar(modelo));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ResponseUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ResponseUtil.parserMensagem(e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody ModeloRequestDto modelo) {
        try {
            return ResponseEntity.ok(modeloService.atualizar(id, modelo));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ResponseUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ResponseUtil.parserMensagem(e.getMessage()));
        }
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            modeloService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ResponseUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ResponseUtil.parserMensagem(e.getMessage()));
        }
    }

}
