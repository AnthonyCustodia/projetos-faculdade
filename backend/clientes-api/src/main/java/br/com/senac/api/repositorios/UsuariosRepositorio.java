package br.com.senac.api.repositorios;

import br.com.senac.api.entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepositorio extends JpaRepository<Usuarios, Long> {
}
