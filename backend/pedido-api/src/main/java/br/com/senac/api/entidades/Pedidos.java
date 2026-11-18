package br.com.senac.api.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

/**
 * Entidade JPA da tabela "pedidos". Um pedido tem vários itens (relação 1:N com PedidosItens).
 */
@Entity
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCliente;

    // @OneToMany(mappedBy = "pedido"): o lado "dono" da relação é o atributo "pedido" em PedidosItens
    // (é lá que fica a coluna pedido_id). Esta lista só espelha os itens; não há cascade.
    // @JsonManagedReference: lado que É serializado no JSON. Junto com @JsonBackReference em
    // PedidosItens, evita o loop infinito pedido -> itens -> pedido -> itens...
    // Obs.: sem "= new ArrayList<>()" a lista começa null (um pedido recém-criado volta "itens": null).
    @OneToMany(mappedBy = "pedido")
    @JsonManagedReference
    private List<PedidosItens> itens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<PedidosItens> getItens() {
        return itens;
    }

    public void setItens(List<PedidosItens> itens) {
        this.itens = itens;
    }
}
