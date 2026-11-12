package br.com.senac.api.services;

import br.com.senac.api.dtos.PedidoItemRequestDto;
import br.com.senac.api.dtos.PedidosRequestDto;
import br.com.senac.api.entidades.Pedidos;
import br.com.senac.api.entidades.PedidosItens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidosComItensService {

    @Autowired
    private PedidosService pedidosService;

    @Autowired
    private PedidosItensService pedidosItensService;

    public Pedidos criarPedidoItens(PedidosRequestDto pedido) {
        Pedidos pedidoRetorno = this.pedidosService.criar(pedido);

        if(pedido.getItens() != null && !pedido.getItens().isEmpty()) {
            for(PedidoItemRequestDto item : pedido.getItens()) {

                item.setPedidoId(pedidoRetorno.getId());
                PedidosItens retorno = this.pedidosItensService.criar(item);

                pedidoRetorno.getItens().add(retorno);

            }

            return this.pedidosService.listarPorId(pedidoRetorno.getId());
        }

        return pedidoRetorno;

    }
}
