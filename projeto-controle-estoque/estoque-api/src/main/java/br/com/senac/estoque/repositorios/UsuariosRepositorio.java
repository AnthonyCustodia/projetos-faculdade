package br.com.senac.estoque.repositorios;

import br.com.senac.estoque.entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepositorio extends JpaRepository<Usuarios, Long> {

    Optional<Usuarios> findByEmail(String email);

    boolean existsByEmail(String email);
}
