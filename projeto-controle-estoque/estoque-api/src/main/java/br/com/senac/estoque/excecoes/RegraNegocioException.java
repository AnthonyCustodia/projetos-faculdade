package br.com.senac.estoque.excecoes;

// Regra de negocio violada (dado invalido, estoque insuficiente...) -> HTTP 400
public class RegraNegocioException extends RuntimeException {
    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
