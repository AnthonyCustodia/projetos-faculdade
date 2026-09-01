import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class Teste {

    public static Map<String, CalcularFreteStrategy> ESTRATEGIAS = Map.of(
            "SEDEX", new CalculoFreteSedexStrategy(),
            "TRANSPORTADORA", new CalculoFreteTransportadoraStrategy()
    );

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.println("Informe a modalidade de frete: ");
        System.out.println("SEDEX");
        System.out.println("TRANSPORTADORA");

        var frete = scanner.nextLine();
        var contexto = new ContextoCalculoFrete(frete,
                new BigDecimal("5"),
                new BigDecimal("15"));

        CalcularFreteStrategy estrategia = ESTRATEGIAS.get(contexto.tipoFrete());

        System.out.println("Valor do frete: " + estrategia.calcular(contexto));
    }

}
