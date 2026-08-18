package factorymethod;

public class Whatsapp implements ServicoNotificacao {

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando WhatsApp: " + mensagem);
    }
}
