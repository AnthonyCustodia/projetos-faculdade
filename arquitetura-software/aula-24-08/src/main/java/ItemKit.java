import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemKit implements ItemVenda {

    private List<ItemIndividual> itens = new ArrayList<>();

    public void add(ItemIndividual item) {
        this.itens.add(item);
    }

    @Override
    public BigDecimal valorTotal() {
        return this.itens.stream()
                .map(ItemVenda::valorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
