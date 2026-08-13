package br.com.senac.imc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/imc")
public class ImcController {

    Double imc = 0.0;
    String mensagem = "";

    @RequestMapping("/calcular")
    public ResponseEntity<String> entrada (
            @RequestParam double peso,
            @RequestParam double altura
    ) {
        imc = peso / (altura*altura);

        if (imc < 18.5) {
            mensagem = "Abaixo do peso";
        } else if (imc < 25) {
            mensagem = "Peso normal";
        } else if (imc < 30) {
            mensagem = "Sobrepeso";
        } else if (imc < 35) {
            mensagem = "Obesidade Grau 1";
        } else if (imc < 40) {
            mensagem = "Obesidade Grau 2";
        } else {
            mensagem = "Obesidade Grau 3 (Mórbida)";
        }

        return ResponseEntity.ok("Peso: " + peso + "\nAltura: " + altura
                                + "\n\nIMC: " + imc
                                + "\n\n" + mensagem);
    }

}
