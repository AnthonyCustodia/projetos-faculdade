package br.com.senac.api.dtos;

/**
 * Dados recebidos no corpo (JSON) para criar/atualizar um pedido: { "nomeCliente": "..." }
 * Os itens são cadastrados à parte, em POST /pedidosItens/criar (informando o pedidoId).
 */
public class PedidosRequestDto {
    private String nomeCliente;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
