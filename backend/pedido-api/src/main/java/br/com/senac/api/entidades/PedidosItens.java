package br.com.senac.api.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entidade JPA da tabela "pedidos_itens". Cada item pertence a um único pedido (relação N:1).
 */
@Entity
public class PedidosItens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double quantidade;

    // Lado "dono" da relação: cria a coluna pedido_id (chave estrangeira para pedidos.id).
    // @JsonBackReference: este campo NÃO aparece no JSON (evita o loop com Pedidos.itens).
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    @JsonBackReference
    private Pedidos pedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }
}
