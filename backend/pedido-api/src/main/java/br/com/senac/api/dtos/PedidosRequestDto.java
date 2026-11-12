package br.com.senac.api.dtos;

import java.util.List;

public class PedidosRequestDto {
    private String nomeCliente;

    private List<PedidoItemRequestDto> itens;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<PedidoItemRequestDto> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItemRequestDto> itens) {
        this.itens = itens;
    }
}
