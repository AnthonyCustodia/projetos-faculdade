package br.com.senac.estoque.dtos;

// Corpo do POST /produtos/{id}/movimentacoes. O produto vem pela URL.
// Exemplo: { "tipo": "ENTRADA", "quantidade": 10 }
public class MovimentacoesRequestDto {
    private String tipo;
    private Integer quantidade;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
