package br.com.senac.paciente_api.controllers;

import br.com.senac.paciente_api.dtos.PacientesRequestDto;
import br.com.senac.paciente_api.entidades.Pacientes;
import br.com.senac.paciente_api.services.PacientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/paciente")
public class PacientesController {

    @Autowired
    private PacientesService pacientesService;

    @GetMapping("/listar")
    public ResponseEntity<List<Pacientes>> listarTodos() {
        return ResponseEntity.ok(pacientesService.listarTodos());
    }

    @PostMapping("/criar")
    public ResponseEntity<Pacientes> criar(@RequestBody PacientesRequestDto paciente) {
        try {
            return ResponseEntity
                    .status(201)
                    .body(pacientesService.criar(paciente));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Pacientes> atualizar(
            @PathVariable Long id,
            @RequestBody PacientesRequestDto paciente
    ) {
        try {
            return ResponseEntity.ok(pacientesService.atualizar(
                    id,paciente
            ));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            pacientesService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }
}
