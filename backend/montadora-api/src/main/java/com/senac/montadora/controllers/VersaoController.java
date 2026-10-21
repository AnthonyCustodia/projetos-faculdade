package com.senac.montadora.controllers;

import com.senac.montadora.dtos.VersaoRequestDto;
import com.senac.montadora.services.VersaoService;
import com.senac.montadora.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/versao")
public class VersaoController {

    @Autowired
    private VersaoService versaoService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarVersoes() {
        return ResponseEntity.ok(versaoService.listarVersoes());
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody VersaoRequestDto versao) {
        try {
            return ResponseEntity.status(201).body(versaoService.criar(versao));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ResponseUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ResponseUtil.parserMensagem(e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody  VersaoRequestDto versao) {
        try {
            return ResponseEntity.ok().body(versaoService.atualizar(id, versao));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ResponseUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ResponseUtil.parserMensagem(e.getMessage()));
        }
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            versaoService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ResponseUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ResponseUtil.parserMensagem(e.getMessage()));
        }
    }

}
