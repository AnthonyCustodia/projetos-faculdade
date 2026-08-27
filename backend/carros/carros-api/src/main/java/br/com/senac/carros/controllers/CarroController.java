package br.com.senac.carros.controllers;

import br.com.senac.carros.dtos.CarroDto;
import br.com.senac.carros.entities.Carro;
import br.com.senac.carros.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carros")
@CrossOrigin
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping("/listar")
    public ResponseEntity<List<Carro>> listar() {
        return ResponseEntity.ok(carroService.carroList());
    }

    @PostMapping("/criar")
    public ResponseEntity<Carro> adicionar(@RequestBody CarroDto carro) {
        return ResponseEntity.status(201).body(carroService.adicionar(carro));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Carro> atualizar(@PathVariable Long id, @RequestBody CarroDto carro) {
        return ResponseEntity.ok(carroService.atualizar(id, carro));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            carroService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Carro> listarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(carroService.listarPorId(id));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
