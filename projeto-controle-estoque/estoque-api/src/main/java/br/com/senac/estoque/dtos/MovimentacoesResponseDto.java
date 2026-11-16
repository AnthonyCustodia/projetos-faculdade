package br.com.senac.estoque.dtos;

import br.com.senac.estoque.entidades.Movimentacoes;
import br.com.senac.estoque.entidades.TipoMovimentacao;

import java.time.LocalDateTime;

// O que a API devolve de uma movimentacao. Usa DTO em vez da entidade
// para nao expor os dados do usuario (como a senha).
public class MovimentacoesResponseDto {
    private Long id;
    private TipoMovimentacao tipo;
    private Integer quantidade;
    private LocalDateTime dataHora;
    private Long produtoId;
    private String usuario;

    public MovimentacoesResponseDto(Movimentacoes movimentacao) {
        this.id = movimentacao.getId();
        this.tipo = movimentacao.getTipo();
        this.quantidade = movimentacao.getQuantidade();
        this.dataHora = movimentacao.getDataHora();
        this.produtoId = movimentacao.getProduto().getId();
        this.usuario = movimentacao.getUsuario().getNome();
    }

    public Long getId() {
        return id;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public String getUsuario() {
        return usuario;
    }
}
