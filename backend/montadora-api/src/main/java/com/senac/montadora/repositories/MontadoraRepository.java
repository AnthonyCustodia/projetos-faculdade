package com.senac.montadora.repositories;

import com.senac.montadora.entities.Montadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MontadoraRepository extends JpaRepository<Montadora, Long> {
}
