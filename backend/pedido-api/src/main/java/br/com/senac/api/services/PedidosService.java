package br.com.senac.api.services;

import br.com.senac.api.dtos.PedidosRequestDto;
import br.com.senac.api.entidades.Pedidos;
import br.com.senac.api.respositorios.PedidosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * CRUD de pedidos. Os erros de regra de negócio são lançados como RuntimeException
 * e o controller os transforma em resposta 400 { "mensagem": "..." }.
 */
@Service
public class PedidosService {
    @Autowired
    private PedidosRepositorio pedidosRepositorio;

    public List<Pedidos> listar() {
        return pedidosRepositorio.findAll();
    }

    public Pedidos criar(PedidosRequestDto pedido) {
        Pedidos pedidoSaida = this.pedidosRequestDtoToPedidos(pedido);

        return pedidosRepositorio.save(pedidoSaida);
    }

    public Pedidos atualizar(Long id, PedidosRequestDto pedido) {
        if(pedidosRepositorio.existsById(id)) {
            Pedidos pedidoSaida = this.pedidosRequestDtoToPedidos(pedido);
            // Com o id preenchido, save() faz UPDATE em vez de INSERT.
            pedidoSaida.setId(id);

            return pedidosRepositorio.save(pedidoSaida);
        }

        throw new RuntimeException("Pedido não encontrado!");
    }

    /**
     * Obs.: pedido que tem itens não pode ser apagado assim. A tabela de itens tem chave
     * estrangeira (pedido_id NOT NULL) e não há cascade, então o banco recusa o DELETE.
     */
    public void deletar(Long id) {
        if(pedidosRepositorio.existsById(id)) {
            pedidosRepositorio.deleteById(id);
            return;
        }

        throw new RuntimeException("Pedido não encontrado!");
    }

    public Pedidos listarPorId(Long id) {
        Optional<Pedidos> pedidoRetorno = pedidosRepositorio.findById(id);
        if(pedidoRetorno.isPresent()) {
            return pedidoRetorno.get();
        }

        throw new RuntimeException("Pedido não encontrado!");

    }

    // Converte o DTO (dados do JSON) na entidade JPA (o que vai para o banco).
    private Pedidos pedidosRequestDtoToPedidos(PedidosRequestDto entrada) {
        Pedidos saida = new Pedidos();

        saida.setNomeCliente(entrada.getNomeCliente());

        return saida;
    }
}
