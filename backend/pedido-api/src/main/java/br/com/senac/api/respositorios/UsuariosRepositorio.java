package br.com.senac.api.respositorios;

import br.com.senac.api.entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepositorio extends JpaRepository<Usuarios, Long> {
    // "Query method": o Spring Data gera o SQL a partir do nome do método
    // -> SELECT * FROM usuarios WHERE email = ?
    // Usado no login (UsuariosService) e na validação de cada requisição (SecurityFilter).
    // Optional: pode não existir usuário com esse email (evita retornar null).
    Optional<Usuarios> findByEmail(String email);
}
