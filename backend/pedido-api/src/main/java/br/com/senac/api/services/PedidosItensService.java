package br.com.senac.api.services;

import br.com.senac.api.dtos.PedidosItensRequestDto;
import br.com.senac.api.entidades.PedidosItens;
import br.com.senac.api.respositorios.PedidosItensRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * CRUD de itens de pedido. Todo item precisa estar ligado a um pedido existente (pedidoId).
 */
@Service
public class PedidosItensService {
    @Autowired
    private PedidosItensRepositorio pedidosItensRepositorio;

    @Autowired
    private PedidosService pedidosService;

    public List<PedidosItens> listar() {
        return pedidosItensRepositorio.findAll();
    }

    public PedidosItens criar(PedidosItensRequestDto item) {
        PedidosItens itemSaida = this.pedidosItensRequestDtoToPedidosItens(item);

        return pedidosItensRepositorio.save(itemSaida);
    }

    public PedidosItens atualizar(Long id, PedidosItensRequestDto item) {
        if(pedidosItensRepositorio.existsById(id)) {
            PedidosItens itemSaida = this.pedidosItensRequestDtoToPedidosItens(item);
            // Com o id preenchido, save() faz UPDATE em vez de INSERT.
            itemSaida.setId(id);

            return pedidosItensRepositorio.save(itemSaida);
        }

        throw new RuntimeException("Item não encontrado!");
    }

    public void deletar(Long id) {
        if(pedidosItensRepositorio.existsById(id)) {
            pedidosItensRepositorio.deleteById(id);
            return;
        }

        throw new RuntimeException("Item não encontrado!");
    }

    public PedidosItens listarPorId(Long id) {
        Optional<PedidosItens> itemRetorno = pedidosItensRepositorio.findById(id);
        if(itemRetorno.isPresent()) {
            return itemRetorno.get();
        }

        throw new RuntimeException("Item não encontrado!");

    }

    // Converte o DTO na entidade. Busca o pedido pelo pedidoId: se ele não existir,
    // listarPorId() lança "Pedido não encontrado!" e o item não é salvo.
    private PedidosItens pedidosItensRequestDtoToPedidosItens(PedidosItensRequestDto entrada) {
        PedidosItens saida = new PedidosItens();

        saida.setQuantidade(entrada.getQuantidade());
        saida.setPedido(pedidosService.listarPorId(entrada.getPedidoId()));

        return saida;
    }
}
