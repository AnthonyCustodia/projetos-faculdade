package br.com.senac.api.repositorios;

import br.com.senac.api.entidades.Marcas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcasRepositorio extends JpaRepository<Marcas, Long> {
}
