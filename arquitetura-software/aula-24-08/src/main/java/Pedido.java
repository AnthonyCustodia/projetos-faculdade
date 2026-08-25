import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Pedido {

    private LocalDate data;
    private List<ItemVenda> itens;

    public Pedido(LocalDate data, List<ItemVenda> itens) {
        this.data = data;
        this.itens = itens;
    }

    public LocalDate getData() {
        return data;
    }

    public void add(ItemVenda item) {
        this.itens.add((item));
    }

    public BigDecimal valorTotal() {
        return this.itens.stream()
                .map(ItemVenda::valorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
