package br.com.senac.estoque.controllers;

import br.com.senac.estoque.dtos.MovimentacoesRequestDto;
import br.com.senac.estoque.dtos.MovimentacoesResponseDto;
import br.com.senac.estoque.entidades.Usuarios;
import br.com.senac.estoque.services.MovimentacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Movimentacoes sempre pertencem a um produto, por isso a URL comeca em /produtos/{produtoId}.
// So existem GET e POST: movimentacao nao pode ser alterada nem excluida.
@RestController
@RequestMapping("/produtos/{produtoId}/movimentacoes")
public class MovimentacoesController {

    @Autowired
    private MovimentacoesService movimentacoesService;

    @GetMapping
    public ResponseEntity<List<MovimentacoesResponseDto>> listar(@PathVariable Long produtoId) {
        return ResponseEntity.ok(movimentacoesService.listarPorProduto(produtoId));
    }

    // @AuthenticationPrincipal: o usuario logado, colocado no contexto pelo SecurityFilter
    @PostMapping
    public ResponseEntity<MovimentacoesResponseDto> criar(@PathVariable Long produtoId,
                                                          @RequestBody MovimentacoesRequestDto movimentacao,
                                                          @AuthenticationPrincipal Usuarios usuario) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movimentacoesService.criar(produtoId, movimentacao, usuario));
    }
}
