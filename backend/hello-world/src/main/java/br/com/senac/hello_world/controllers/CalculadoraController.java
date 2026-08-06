package br.com.senac.hello_world.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/calculadora")
public class CalculadoraController {

    @GetMapping("/somar")
    public ResponseEntity<Integer> somar(@RequestParam int n1, @RequestParam int n2) {
        return ResponseEntity.ok(n1 + n2);
    }

    @GetMapping("/subtrair")
    public ResponseEntity<Integer> sub(@RequestParam int n1, @RequestParam int n2) {
        return ResponseEntity.ok(n1 - n2);
    }

    @GetMapping("/multiplicar")
    public ResponseEntity<Double> mult(@RequestParam double n1, @RequestParam double n2) {
        double result = n1 * n2;
        return ResponseEntity.ok(result);
    }

    @GetMapping("/dividir")
    public ResponseEntity<Double> div(@RequestParam double n1, @RequestParam double n2) {
        double result = n1 / n2;
        return ResponseEntity.ok(result);
    }

}
