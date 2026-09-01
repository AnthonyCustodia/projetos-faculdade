import java.math.BigDecimal;

public class CalcularFreteMotoboyStrategy implements CalcularFreteStrategy {

    @Override
    public BigDecimal calcular(ContextoCalculoFrete contexto) {
        if (contexto.distancia().compareTo(new BigDecimal("30.0")) > 0) {
            throw new RuntimeException("Distancia superior a permitida!");
        }
        if (contexto.pesoMercadoria().compareTo(new BigDecimal("2")) > 0) {
            throw new RuntimeException("Peso superior ao permitido!");
        }

        return contexto.distancia().multiply(new BigDecimal("0.1"))
                .add(BigDecimal.TEN);
    }
}
