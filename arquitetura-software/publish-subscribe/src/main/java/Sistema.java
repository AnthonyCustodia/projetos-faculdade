import java.util.function.Consumer;

public class Sistema {
    public static void main(String[] args) {
        final String TOPICO = "faturamento";
        final Broker broker = new Broker();

        Consumer<Mensagem> consumidorFinanceiro = mensagem -> {
            System.out.println("Financeiro recebeu uma mensagem!");
            System.out.println("Mensagem " + mensagem.conteudo().toString());
        };

        broker.inscrever(TOPICO, consumidorFinanceiro);

        broker.enviar(TOPICO, new Mensagem("Hello World"));
    }
}
