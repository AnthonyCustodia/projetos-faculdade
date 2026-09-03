package br.com.senac.api.repository;

import br.com.senac.api.entity.Animais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimaisRepository extends JpaRepository<Animais, Long> {
}
