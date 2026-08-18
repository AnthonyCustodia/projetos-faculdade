package factorymethod;

public class Teste {

    public static void main(String[] args) {

        PedidoService pedido1 = new PedidoService();

        PedidoService pedido2 = new PedidoService();

        pedido1.concluirVenda(new Sms());
        pedido2.concluirVenda(new Whatsapp());
        
    }

}
