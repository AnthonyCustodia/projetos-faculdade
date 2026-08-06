package br.com.senac.hello_world.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/helloWorld")
public class HelloWorldController {

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/numeros")
    public ResponseEntity<String> numeros(@RequestParam int numero1, @RequestParam int numero2) {
        return ResponseEntity.ok("Numero 1: " + numero1 + "\nNumero 2: " + numero2);
    }
}
