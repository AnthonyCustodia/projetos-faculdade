package br.com.senac.estoque.repositorios;

import br.com.senac.estoque.entidades.Movimentacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacoesRepositorio extends JpaRepository<Movimentacoes, Long> {

    // Regra: so lista as movimentacoes de UM produto, nunca todas.
    List<Movimentacoes> findByProdutoIdOrderByIdDesc(Long produtoId);
}
