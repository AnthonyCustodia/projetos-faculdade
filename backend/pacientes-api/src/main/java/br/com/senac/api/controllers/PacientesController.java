package br.com.senac.api.controllers;

import br.com.senac.api.dtos.PacientesDto;
import br.com.senac.api.entities.Pacientes;
import br.com.senac.api.services.PacientesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/paciente")
@CrossOrigin
public class PacientesController {

    @Autowired
    private PacientesServices pacientesServices;

    @GetMapping("/listar")
    public ResponseEntity<List<Pacientes>> listar() {
        return ResponseEntity.ok(pacientesServices.listar());
    }

    @PostMapping("/criar")
    public ResponseEntity<Pacientes> criar(@RequestBody PacientesDto pacientes) {
        try {
            return ResponseEntity.ok(pacientesServices.criar(pacientes));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Pacientes> atualizar(@RequestBody PacientesDto pacientes, @PathVariable Long id) {
        return ResponseEntity.ok(pacientesServices.atualizar(pacientes, id));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.pacientesServices.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
