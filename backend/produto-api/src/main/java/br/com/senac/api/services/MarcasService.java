package br.com.senac.api.services;

import br.com.senac.api.dtos.MarcasRequestDto;
import br.com.senac.api.entidades.Marcas;
import br.com.senac.api.repositorios.MarcasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcasService {

    @Autowired
    private MarcasRepositorio marcasRepositorio;

    public List<Marcas> listarTodos() {
        return marcasRepositorio.findAll();
    }

    public Marcas criar(MarcasRequestDto marca) {
        Marcas marcasPersit = this.marcasRequestDtoParaMarcas(marca);

        return marcasRepositorio.save(marcasPersit);
    }

    public Marcas atualizar(Long id, MarcasRequestDto marca) {
        if(marcasRepositorio.existsById(id)) {
            Marcas marcasPersit = this.marcasRequestDtoParaMarcas(marca);
            marcasPersit.setId(id);

            return marcasRepositorio.save(marcasPersit);
        }

        throw new RuntimeException("Marca não encontrada!");
    }

    public void deletar(Long id) {
        if(marcasRepositorio.existsById(id)) {
            marcasRepositorio.deleteById(id);
            return;
        }

        throw new RuntimeException("Marca não encontrada!");
    }

    public Marcas listarPorId(Long id) {
        Optional<Marcas> marcaRetorno = marcasRepositorio.findById(id);
        if(marcaRetorno.isPresent()) {
            return marcaRetorno.get();
        }

        throw new RuntimeException("Marca não encontrada!");
    }

    private Marcas marcasRequestDtoParaMarcas
            (MarcasRequestDto entrada) {
        Marcas saida = new Marcas();
        saida.setNome(entrada.getNome());

        return saida;
    }
}
