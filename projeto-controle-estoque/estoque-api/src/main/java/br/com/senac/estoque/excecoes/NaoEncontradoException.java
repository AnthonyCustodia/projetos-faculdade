package br.com.senac.estoque.excecoes;

// Registro pedido nao existe -> HTTP 404
public class NaoEncontradoException extends RuntimeException {
    public NaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
