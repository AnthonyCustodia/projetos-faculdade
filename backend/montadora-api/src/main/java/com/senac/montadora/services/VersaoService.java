package com.senac.montadora.services;

import com.senac.montadora.dtos.VersaoRequestDto;
import com.senac.montadora.entities.Modelo;
import com.senac.montadora.entities.Versao;
import com.senac.montadora.repositories.ModeloRepository;
import com.senac.montadora.repositories.VersaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersaoService {

    @Autowired
    private VersaoRepository versaoRepository;

    @Autowired
    private ModeloRepository modeloRepository;

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
            versaoPersist.setId(id);

            return versaoRepository.save(versaoPersist);
        }
        throw new RuntimeException("Versao nao encontrada!");
    }

    public void deletar(Long id) {
        if (versaoRepository.existsById(id)) {
            versaoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Versao nao encontrada!");
        }
    }

    private Versao dtoToVersao(VersaoRequestDto entry) {
        Modelo modelo = modeloRepository.findById(entry.getModeloId())
            .orElseThrow(() -> new RuntimeException("Modelo nao encontrado!"));

        Versao exit = new Versao();

        exit.setMotor(entry.getMotor());
        exit.setModelo(modelo);

        return exit;
    }
}
