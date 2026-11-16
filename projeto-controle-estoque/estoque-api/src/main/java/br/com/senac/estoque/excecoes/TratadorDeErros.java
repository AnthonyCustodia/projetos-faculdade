package br.com.senac.estoque.excecoes;

import br.com.senac.estoque.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

// Centraliza o try/catch que antes ficava repetido em cada metodo dos controllers.
// Toda resposta de erro tem o formato { "mensagem": "..." }.
@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Map<String, String>> regraNegocio(RegraNegocioException e) {
        return ResponseEntity.badRequest().body(ResponseUtil.parserMensagem(e.getMessage()));
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> naoEncontrado(NaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtil.parserMensagem(e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> jsonInvalido(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(ResponseUtil.parserMensagem("Corpo da requisição inválido!"));
    }
}
