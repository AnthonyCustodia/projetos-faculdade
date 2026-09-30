package br.com.senac.paciente_api.repositorios;

import br.com.senac.paciente_api.entidades.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacientesRepositorio
        extends JpaRepository<Pacientes, Long> {
}
