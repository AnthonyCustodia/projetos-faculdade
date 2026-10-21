package com.senac.montadora.services;

import com.senac.montadora.dtos.MontadoraRequestDto;
import com.senac.montadora.entities.Montadora;
import com.senac.montadora.repositories.MontadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MontadoraService {

    @Autowired
    private MontadoraRepository montadoraRepository;

    public List<Montadora> listarMontadoras() {
        return montadoraRepository.findAll();
    }

    public Montadora criar(MontadoraRequestDto montadora) {
        Montadora montadoraPersist = this.dtoToMontadora(montadora);

        return montadoraRepository.save(montadoraPersist);
    }

    public Montadora atualizar(Long id, MontadoraRequestDto montadora) {
        if (montadoraRepository.existsById(id)) {
            Montadora montadoraPersist = this.dtoToMontadora(montadora);
            montadoraPersist.setId(id);

            return montadoraRepository.save(montadoraPersist);
        }
        throw new RuntimeException("Montadora nao encontrada!");
    }

    public void deletar(Long id) {
        if (montadoraRepository.existsById(id)) {
            montadoraRepository.deleteById(id);
        } else {
            throw new RuntimeException("Montadora nao encontrada!");
        }
    }

    private Montadora dtoToMontadora(MontadoraRequestDto entry) {
        Montadora exit = new Montadora();

        exit.setNome(entry.getNome());

        return exit;
    }

}
