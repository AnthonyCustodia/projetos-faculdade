package br.com.senac.api.services;

import br.com.senac.api.dtos.ClienteEnderecoRequestDto;
import br.com.senac.api.dtos.ClientesRequestDto;
import br.com.senac.api.entidades.Clientes;
import br.com.senac.api.entidades.Enderecos;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientesEnderecosService {

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private EndercosService endercosService;

    public Clientes criarClienteEnderecos(ClientesRequestDto cliente) {
        Clientes clienteRetorno = this.clientesService.criar(cliente);

        if (cliente.getEnderecos() != null && !cliente.getEnderecos().isEmpty()) {
            for (ClienteEnderecoRequestDto end : cliente.getEnderecos()) {

                this.endercosService.criar(end);
            }
        }
        return clienteRetorno;
    }

}
