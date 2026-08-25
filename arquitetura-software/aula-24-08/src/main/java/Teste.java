import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Teste {

    public static void main(String[] args) {

        var mouse = new Produto("Mouse", new BigDecimal("100.50"));
        var teclado = new Produto("Teclado", new BigDecimal("150.99"));
        var processador = new Produto("Processador", new BigDecimal("958.99"));

        var pedido1 = new Pedido(LocalDate.now(), new ArrayList<>());

        pedido1.add(new ItemIndividual(processador, BigDecimal.ONE));
        pedido1.add(new ItemIndividual(mouse, BigDecimal.TWO));

        var kitGamer = new ItemKit();
        kitGamer.add(new ItemIndividual(mouse, BigDecimal.ONE));
        kitGamer.add(new ItemIndividual(teclado, BigDecimal.TWO));

        pedido1.add(kitGamer);

        System.out.println("Pedido com data " + pedido1.getData() +
                " e valor " + pedido1.valorTotal());

    }

}
