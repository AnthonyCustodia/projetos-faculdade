import java.math.BigDecimal;

public record ItemIndividual (Produto produto, BigDecimal quantidade) implements ItemVenda {

    @Override
    public BigDecimal valorTotal() {
        return produto.valor().multiply(quantidade);
    }
}
