package factorymethod;

public class PedidoService {

    public void concluirVenda(ServicoNotificacao servicoNotificacao){
        servicoNotificacao.enviar("Venda concluida!");
    }

}
