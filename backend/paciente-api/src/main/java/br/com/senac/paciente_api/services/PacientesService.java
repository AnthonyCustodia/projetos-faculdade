package br.com.senac.paciente_api.services;

import br.com.senac.paciente_api.dtos.PacientesRequestDto;
import br.com.senac.paciente_api.entidades.Pacientes;
import br.com.senac.paciente_api.repositorios.PacientesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacientesService {

    @Autowired
    private PacientesRepositorio pacientesRepositorio;

    public List<Pacientes> listarTodos() {
        return pacientesRepositorio.findAll();
    }

    public Pacientes criar(PacientesRequestDto paciente) {
        this.validarRegrasNegocio(paciente);

        Pacientes pacientesPersit = this
                .pacientesRequestDtoParaPacientes(paciente);

        return pacientesRepositorio.save(pacientesPersit);

    }

    public Pacientes atualizar(
            Long id,
            PacientesRequestDto paciente
    ) {

        this.validarRegrasNegocio(paciente);

        Optional<Pacientes> pacientesResultado =
                pacientesRepositorio.findById(id);
        if(pacientesResultado.isPresent()) {
            Pacientes pacientesPersit = this
                    .pacientesRequestDtoParaPacientes(paciente);
            pacientesPersit.setId(id);

            return pacientesRepositorio.save(pacientesPersit);
        }

        throw new RuntimeException("Paciente não encontrado!");
    }

    public void deletar(Long id) {
        if(pacientesRepositorio.existsById(id)) {
            pacientesRepositorio.deleteById(id);
            return;
        }

        throw new RuntimeException("paciente não encontrado!");
    }

    private Pacientes pacientesRequestDtoParaPacientes
            (PacientesRequestDto entrada) {
        Pacientes saida = new Pacientes();
        saida.setNome(entrada.getNome());
        saida.setIdade(entrada.getIdade());
        saida.setDataEntrada(entrada.getDataEntrada());

        return saida;
    }

    private void validarRegrasNegocio(PacientesRequestDto paciente) {
        if(paciente.getIdade() < 18) {
            throw new RuntimeException("Paciente menor de idade, entrada não \n" +
                    "permitida");
        }


    }
}
