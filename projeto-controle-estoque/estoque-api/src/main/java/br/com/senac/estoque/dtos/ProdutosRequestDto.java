package br.com.senac.estoque.dtos;

// Dados aceitos no cadastro e na edicao de produto.
// De proposito NAO tem "estoque" nem "ativo": estoque so muda por movimentacao
// e o status so muda pelos endpoints de ativar/desativar.
public class ProdutosRequestDto {
    private String codigoBarras;
    private String nome;
    private String descricao;

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
