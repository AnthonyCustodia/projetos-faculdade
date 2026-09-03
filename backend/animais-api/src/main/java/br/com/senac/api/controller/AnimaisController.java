package br.com.senac.api.controller;

import br.com.senac.api.dto.AnimaisDto;
import br.com.senac.api.entity.Animais;
import br.com.senac.api.service.AnimaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/animais")
@CrossOrigin
public class AnimaisController {

    @Autowired
    private AnimaisService animaisService;

    @PostMapping("/criar")
    public ResponseEntity<Animais> criar(@RequestBody AnimaisDto animais) {
        return ResponseEntity.ok(animaisService.criar(animais));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Animais> atualizar(@PathVariable Long id, @RequestBody AnimaisDto animais) {
        return ResponseEntity.ok(animaisService.atualizar(id, animais));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Animais>> listar() {
        return ResponseEntity.ok(animaisService.listar());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        animaisService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
