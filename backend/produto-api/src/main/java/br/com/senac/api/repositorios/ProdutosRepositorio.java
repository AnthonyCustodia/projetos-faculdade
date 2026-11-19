package br.com.senac.api.repositorios;

import br.com.senac.api.entidades.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepositorio extends JpaRepository<Produtos, Long> {
}
