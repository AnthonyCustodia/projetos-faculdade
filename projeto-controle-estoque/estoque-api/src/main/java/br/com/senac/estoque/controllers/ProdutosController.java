package br.com.senac.estoque.controllers;

import br.com.senac.estoque.dtos.ProdutosRequestDto;
import br.com.senac.estoque.entidades.Produtos;
import br.com.senac.estoque.services.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Nao ha @DeleteMapping de proposito: produto nao pode ser deletado, so desativado.
@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;

    @GetMapping
    public ResponseEntity<List<Produtos>> listar() {
        return ResponseEntity.ok(produtosService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtos> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtosService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Produtos> criar(@RequestBody ProdutosRequestDto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtosService.criar(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produtos> atualizar(@PathVariable Long id, @RequestBody ProdutosRequestDto produto) {
        return ResponseEntity.ok(produtosService.atualizar(id, produto));
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Produtos> desativar(@PathVariable Long id) {
        return ResponseEntity.ok(produtosService.alterarStatus(id, false));
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Produtos> ativar(@PathVariable Long id) {
        return ResponseEntity.ok(produtosService.alterarStatus(id, true));
    }
}
