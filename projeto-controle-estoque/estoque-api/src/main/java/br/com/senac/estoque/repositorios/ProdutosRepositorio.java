package br.com.senac.estoque.repositorios;

import br.com.senac.estoque.entidades.Produtos;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutosRepositorio extends JpaRepository<Produtos, Long> {

    List<Produtos> findAllByOrderByIdAsc();

    boolean existsByCodigoBarras(String codigoBarras);

    boolean existsByCodigoBarrasAndIdNot(String codigoBarras, Long id);

    // Trava a linha do produto ate o fim da transacao. Assim, duas movimentacoes
    // ao mesmo tempo no mesmo produto nao "perdem" uma das atualizacoes de estoque.
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Produtos p where p.id = :id")
    Optional<Produtos> buscarPorIdParaAtualizarEstoque(@Param("id") Long id);
}
