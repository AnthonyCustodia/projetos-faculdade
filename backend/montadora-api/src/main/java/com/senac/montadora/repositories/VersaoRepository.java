package com.senac.montadora.repositories;

import com.senac.montadora.entities.Versao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersaoRepository extends JpaRepository<Versao, Long> {
}
