package br.com.senac.api.services;

import br.com.senac.api.dtos.ClientesRequestDto;
import br.com.senac.api.entidades.Clientes;
import br.com.senac.api.repositorios.ClientesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepositorio clientesRepositorio;

    public List<Clientes> listar() {
        return clientesRepositorio.findAll();
    }


    public Clientes criar(ClientesRequestDto cliente) {
        Clientes clienteSaida = new Clientes();
        clienteSaida.setNome(cliente.getNome());
        clienteSaida.setDocumento(cliente.getDocumento());
        clienteSaida.setEmail(cliente.getEmail());
        clienteSaida.setDataNascimeto(cliente.getDataNascimento());

        return clientesRepositorio.save(clienteSaida);
    }

}
