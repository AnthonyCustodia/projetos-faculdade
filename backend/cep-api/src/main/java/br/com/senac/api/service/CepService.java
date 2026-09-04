package br.com.senac.api.service;

import br.com.senac.api.dto.CepDto;
import br.com.senac.api.entity.Cep;
import br.com.senac.api.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CepService {

    @Autowired
    private CepRepository cepRepository;

    public List<Cep> listar() {
        return cepRepository.findAll();
    }

    public Cep criar(CepDto cep) {
        Cep cepPersist = new Cep();

        cepPersist.setCep(cep.getCep());
        cepPersist.setLogradouro(cep.getLogradouro());
        cepPersist.setBairro(cep.getBairro());
        cepPersist.setCidade(cep.getCidade());
        cepPersist.setUf(cep.getUf());

        return cepRepository.save(cepPersist);
    }

}
