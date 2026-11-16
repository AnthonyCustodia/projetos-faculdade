package br.com.senac.estoque.services;

import br.com.senac.estoque.dtos.MovimentacoesRequestDto;
import br.com.senac.estoque.dtos.MovimentacoesResponseDto;
import br.com.senac.estoque.entidades.Movimentacoes;
import br.com.senac.estoque.entidades.Produtos;
import br.com.senac.estoque.entidades.TipoMovimentacao;
import br.com.senac.estoque.entidades.Usuarios;
import br.com.senac.estoque.excecoes.NaoEncontradoException;
import br.com.senac.estoque.excecoes.RegraNegocioException;
import br.com.senac.estoque.repositorios.MovimentacoesRepositorio;
import br.com.senac.estoque.repositorios.ProdutosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacoesService {

    @Autowired
    private MovimentacoesRepositorio movimentacoesRepositorio;

    @Autowired
    private ProdutosRepositorio produtosRepositorio;

    @Autowired
    private ProdutosService produtosService;

    @Transactional(readOnly = true)
    public List<MovimentacoesResponseDto> listarPorProduto(Long produtoId) {
        produtosService.listarPorId(produtoId); // 404 se o produto nao existir

        return movimentacoesRepositorio.findByProdutoIdOrderByIdDesc(produtoId)
                .stream()
                .map(MovimentacoesResponseDto::new)
                .toList();
    }

    // @Transactional: grava a movimentacao E atualiza o estoque juntos.
    // Se qualquer passo falhar, nada e salvo.
    @Transactional
    public MovimentacoesResponseDto criar(Long produtoId, MovimentacoesRequestDto entrada, Usuarios usuario) {
        TipoMovimentacao tipo = converterTipo(entrada.getTipo());
        Integer quantidade = entrada.getQuantidade();
        if (quantidade == null || quantidade <= 0) {
            throw new RegraNegocioException("A quantidade deve ser maior que zero!");
        }

        Produtos produto = produtosRepositorio.buscarPorIdParaAtualizarEstoque(produtoId)
                .orElseThrow(() -> new NaoEncontradoException("Produto nao encontrado!"));
        if (!produto.getAtivo()) {
            throw new RegraNegocioException("Produto desativado nao pode ser movimentado!");
        }

        if (tipo == TipoMovimentacao.ENTRADA) {
            // Entrada: soma a quantidade ao estoque que ja existe
            produto.setEstoque(produto.getEstoque() + quantidade);
        } else {
            // Saida: subtrai a quantidade do estoque que ja existe
            if (quantidade > produto.getEstoque()) {
                throw new RegraNegocioException(
                        "Estoque insuficiente! Disponivel: " + produto.getEstoque());
            }
            produto.setEstoque(produto.getEstoque() - quantidade);
        }
        produtosRepositorio.save(produto);

        Movimentacoes movimentacao = new Movimentacoes();
        movimentacao.setTipo(tipo);
        movimentacao.setQuantidade(quantidade);
        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setProduto(produto);
        movimentacao.setUsuario(usuario);

        return new MovimentacoesResponseDto(movimentacoesRepositorio.save(movimentacao));
    }

    private TipoMovimentacao converterTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            throw new RegraNegocioException("Informe o tipo da movimentacao: ENTRADA ou SAIDA!");
        }
        try {
            return TipoMovimentacao.valueOf(tipo.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RegraNegocioException("Tipo de movimentacao invalido! Use ENTRADA ou SAIDA.");
        }
    }
}
