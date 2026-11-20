package br.com.senac.estoque.controllers;

import br.com.senac.estoque.dtos.TokenResponseDto;
import br.com.senac.estoque.dtos.UsuariosRequestDto;
import br.com.senac.estoque.dtos.UsuariosResponseDto;
import br.com.senac.estoque.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody UsuariosRequestDto usuario) {
        return ResponseEntity.ok(usuariosService.login(usuario));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuariosResponseDto> criar(@RequestBody UsuariosRequestDto usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.criar(usuario));
    }
}
