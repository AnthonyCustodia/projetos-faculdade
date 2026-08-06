import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * Criar lista de consumidores
 * Criar possibilidade de inscrever-se
 * Criar possibilidade de desinscrever-se
 * Criar possibilidade de enviar mensagem
 */

public class Broker {

    private final Map<String, List<Consumer<Mensagem>>> inscritos = new ConcurrentHashMap<>();

    public void inscrever(String topicoDesejado, Consumer<Mensagem> consumidor) {
        List<Consumer<Mensagem>> topico = inscritos.get(topicoDesejado);

        if (topico != null && topico.contains(consumidor)) {
            System.out.println("Consumidor ja cadastrado!");
            return;
        }

        inscritos.computeIfAbsent(topicoDesejado, valor -> new CopyOnWriteArrayList<>()).add(consumidor);
    }

    public void desinscrever(String topicoDesejado, Consumer<Mensagem> consumidor) {
        List<Consumer<Mensagem>> topico = inscritos.get(topicoDesejado);

        if (topico == null || !topico.contains(consumidor)){
            System.out.println("Consumidor nao existe");
            return;
        }

        topico.remove(consumidor);
    }

    public void enviar(String topicoDesejado, Mensagem mensagem) {
        List<Consumer<Mensagem>> topico = inscritos.get(topicoDesejado);

        if (topico != null & !topico.isEmpty()) {
            topico.forEach(consumidor -> {
                consumidor.accept(mensagem);
            });
        }
    }

}
