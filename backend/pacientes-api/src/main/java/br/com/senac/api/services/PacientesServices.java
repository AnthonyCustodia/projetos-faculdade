package br.com.senac.api.services;

import br.com.senac.api.dtos.PacientesDto;
import br.com.senac.api.entities.Pacientes;
import br.com.senac.api.repository.PacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PacientesServices {

    @Autowired
    private PacientesRepository pacientesRepository;

    public List<Pacientes> listar() {
        return pacientesRepository.findAll();
    }

    public Pacientes criar(PacientesDto pacientes) {
        Pacientes pacientesPersist = new Pacientes();

        if (pacientes.getIdade() < 18) {
            throw new RuntimeException("Paciente menor de idade, entrada não permitida");
        }

        pacientesPersist.setNome(pacientes.getNome());
        pacientesPersist.setIdade(pacientes.getIdade());
        pacientesPersist.setDataEntrada(pacientes.getDataEntrada());

        return pacientesRepository.save(pacientesPersist);
    }

    public Pacientes atualizar(PacientesDto pacientes, Long id) {
        Pacientes pacientesPersist = pacientesRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("ID de paciente não encontrado!"));

        pacientesPersist.setNome(pacientes.getNome());
        pacientesPersist.setIdade(pacientes.getIdade());
        pacientesPersist.setDataEntrada(pacientes.getDataEntrada());

        return pacientesRepository.save(pacientesPersist);
    }

    public void deletar(Long id) {
        if (!pacientesRepository.existsById(id)) {
            throw new NoSuchElementException("ID de paciente não encontrado!");
        }
        pacientesRepository.deleteById(id);
    }
}
