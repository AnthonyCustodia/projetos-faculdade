package br.com.senac.api.service;

import br.com.senac.api.dto.CepDto;
import br.com.senac.api.entity.Cep;
import br.com.senac.api.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CepService {

    @Autowired
    private CepRepository cepRepository;

    public List<Cep> listar() {
        return cepRepository.findAll();
    }

    public Cep criar(CepDto cep) {
        this.validarCep(cep);

        Cep cepPersist = new Cep();

        cepPersist.setCep(cep.getCep());
        cepPersist.setLogradouro(cep.getLogradouro());
        cepPersist.setBairro(cep.getBairro());
        cepPersist.setCidade(cep.getCidade());
        cepPersist.setUf(cep.getUf());

        return cepRepository.save(cepPersist);
    }

    public Cep atualizar(Long id, CepDto cep) {
        Cep cepPersist = cepRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("ID nao encontrado"));

        cepPersist.setCep(cep.getCep());
        cepPersist.setLogradouro(cep.getLogradouro());
        cepPersist.setBairro(cep.getBairro());
        cepPersist.setCidade(cep.getCidade());
        cepPersist.setUf(cep.getUf());

        return cepRepository.save(cepPersist);
    }

    public void deletar(Long id) {
        if (!cepRepository.existsById(id)) {
            throw new NoSuchElementException("ID de animal nao encontrado!");
        }
        cepRepository.deleteById(id);
    }

    private void validarCampo(String valor, String nomeCampo) {
        if (valor == null || valor.isBlank()) {
            throw new RuntimeException("Campo " + nomeCampo + " é obrigatório");
        }
    }

    private void validarCep(CepDto cep) {
        validarCampo(cep.getCep(), "CEP");
        validarCampo(cep.getLogradouro(), "logradouro");
        validarCampo(cep.getBairro(), "bairro");
        validarCampo(cep.getCidade(), "cidade");
        validarCampo(cep.getUf(), "uf");
    }
}
