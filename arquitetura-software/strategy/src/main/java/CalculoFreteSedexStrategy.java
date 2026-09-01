import java.math.BigDecimal;

public class CalculoFreteSedexStrategy implements CalcularFreteStrategy {

    @Override
    public BigDecimal calcular(ContextoCalculoFrete contexto) {
        return contexto.pesoMercadoria().multiply(BigDecimal.valueOf(4.5))
                .add(contexto.distancia().multiply(BigDecimal.valueOf(0.20)))
                .add(BigDecimal.valueOf(25.0));
    }
}
