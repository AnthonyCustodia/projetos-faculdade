package com.senac.montadora.services;

import com.senac.montadora.dtos.VersaoRequestDto;
import com.senac.montadora.entities.Versao;
import com.senac.montadora.repositories.VersaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersaoService {

    @Autowired
    private VersaoRepository versaoRepository;

    public List<Versao> listarVersoes() {
        return versaoRepository.findAll();
    }

    public Versao criar(VersaoRequestDto versao) {
        Versao versaoPersist = this.dtoToVersao(versao);

        return versaoRepository.save(versaoPersist);
    }

    public Versao atualizar(Long id, VersaoRequestDto versao) {
        if (versaoRepository.existsById(id)) {
            Versao versaoPersist = this.dtoToVersao(versao);

            return versaoRepository.save(versaoPersist);
        }
        throw new RuntimeException("Versao nao encontrada!");
    }

    public void deletar(Long id) {
        if (versaoRepository.existsById(id)) {
            versaoRepository.deleteById(id);
        }
        throw new RuntimeException("Versao nao encontrada!");
    }

    private Versao dtoToVersao(VersaoRequestDto entry) {
        Versao exit = new Versao();

        exit.setMotor(entry.getMotor());

        return exit;
    }
}
