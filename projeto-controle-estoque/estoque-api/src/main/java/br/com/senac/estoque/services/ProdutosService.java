package br.com.senac.estoque.services;

import br.com.senac.estoque.dtos.ProdutosRequestDto;
import br.com.senac.estoque.entidades.Produtos;
import br.com.senac.estoque.excecoes.NaoEncontradoException;
import br.com.senac.estoque.excecoes.RegraNegocioException;
import br.com.senac.estoque.repositorios.ProdutosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepositorio produtosRepositorio;

    public List<Produtos> listar() {
        return produtosRepositorio.findAllByOrderByIdAsc();
    }

    public Produtos listarPorId(Long id) {
        return produtosRepositorio.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Produto nao encontrado!"));
    }

    public Produtos criar(ProdutosRequestDto entrada) {
        validar(entrada);
        String codigoBarras = entrada.getCodigoBarras().trim();
        if (produtosRepositorio.existsByCodigoBarras(codigoBarras)) {
            throw new RegraNegocioException("Ja existe um produto com este codigo de barras!");
        }

        Produtos produto = new Produtos();
        preencher(produto, entrada);
        produto.setEstoque(0);   // todo produto nasce com estoque zero
        produto.setAtivo(true);

        return produtosRepositorio.save(produto);
    }

    // Altera somente codigo de barras, nome e descricao. Estoque e status ficam como estao.
    public Produtos atualizar(Long id, ProdutosRequestDto entrada) {
        validar(entrada);
        Produtos produto = listarPorId(id);
        if (produtosRepositorio.existsByCodigoBarrasAndIdNot(entrada.getCodigoBarras().trim(), id)) {
            throw new RegraNegocioException("Ja existe outro produto com este codigo de barras!");
        }

        preencher(produto, entrada);
        return produtosRepositorio.save(produto);
    }

    // Nao existe "deletar": o produto so pode ser desativado (e reativado).
    public Produtos alterarStatus(Long id, boolean ativo) {
        Produtos produto = listarPorId(id);
        produto.setAtivo(ativo);
        return produtosRepositorio.save(produto);
    }

    private void preencher(Produtos produto, ProdutosRequestDto entrada) {
        produto.setCodigoBarras(entrada.getCodigoBarras().trim());
        produto.setNome(entrada.getNome().trim());
        produto.setDescricao(entrada.getDescricao() == null ? null : entrada.getDescricao().trim());
    }

    private void validar(ProdutosRequestDto entrada) {
        if (entrada.getCodigoBarras() == null || entrada.getCodigoBarras().isBlank()) {
            throw new RegraNegocioException("O codigo de barras e obrigatorio!");
        }
        if (entrada.getNome() == null || entrada.getNome().isBlank()) {
            throw new RegraNegocioException("O nome do produto e obrigatorio!");
        }
    }
}
