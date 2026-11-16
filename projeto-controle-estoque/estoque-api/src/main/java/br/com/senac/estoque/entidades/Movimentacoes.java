package br.com.senac.estoque.entidades;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// Movimentacao de estoque. Depois de criada nunca e alterada nem excluida
// (por isso as colunas sao "updatable = false" e nao ha PUT/DELETE na API).
@Entity
public class Movimentacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, length = 10)
    private TipoMovimentacao tipo;

    @Column(nullable = false, updatable = false)
    private Integer quantidade;

    @Column(name = "data_hora", nullable = false, updatable = false)
    private LocalDateTime dataHora;

    // Relacionamento: muitas movimentacoes para um produto
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false, updatable = false)
    private Produtos produto;

    // Relacionamento: muitas movimentacoes para um usuario (quem registrou)
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, updatable = false)
    private Usuarios usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
