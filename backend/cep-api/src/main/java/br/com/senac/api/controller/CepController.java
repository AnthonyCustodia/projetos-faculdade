package br.com.senac.api.controller;

import br.com.senac.api.dto.CepDto;
import br.com.senac.api.entity.Cep;
import br.com.senac.api.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ceps")
@CrossOrigin
public class CepController {

    @Autowired
    private CepService cepService;

    @PostMapping("/criar")
    public ResponseEntity<Cep> criar(@RequestBody CepDto cep) {
        try {
            return ResponseEntity.ok(cepService.criar(cep));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Cep> atualizar(@PathVariable Long id, @RequestBody CepDto cep) {
        try {
            return ResponseEntity.ok(cepService.atualizar(id, cep));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cep>> listar() {
        return ResponseEntity.ok(cepService.listar());
    }

    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cepService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
