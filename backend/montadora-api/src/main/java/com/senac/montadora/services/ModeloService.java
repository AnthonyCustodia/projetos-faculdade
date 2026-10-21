package com.senac.montadora.services;

import com.senac.montadora.dtos.ModeloRequestDto;
import com.senac.montadora.entities.Modelo;
import com.senac.montadora.repositories.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    public List<Modelo> listarModelos() {
        return modeloRepository.findAll();
    }

    public Modelo criar(ModeloRequestDto modelo) {
        Modelo modeloPersist = this.dtoToModelo(modelo);

        return modeloRepository.save(modeloPersist);
    }

    public Modelo atualizar(Long id, ModeloRequestDto modelo) {
        if (modeloRepository.existsById(id)) {
            Modelo modeloPersist = this.dtoToModelo(modelo);
            modeloPersist.setId(id);

            return modeloRepository.save(modeloPersist);
        }

        throw new RuntimeException("Modelo nao encontrado!");
    }

    public void deletar(Long id) {
        if (modeloRepository.existsById(id)) {
            modeloRepository.deleteById(id);
        }
    }

    private Modelo dtoToModelo(ModeloRequestDto entry) {
        Modelo exit = new Modelo();

        exit.setNome(entry.getNome());

        return exit;
    }
}
