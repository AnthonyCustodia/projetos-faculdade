package br.com.senac.api.services;

import br.com.senac.api.dtos.ProdutosRequestDto;
import br.com.senac.api.entidades.Produtos;
import br.com.senac.api.repositorios.ProdutosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutosService {
    @Autowired
    private ProdutosRepositorio produtosRepositorio;

    @Autowired
    private MarcasService marcasService;

    public List<Produtos> listarTodos() {
        return produtosRepositorio.findAll();
    }

    public Produtos criar(ProdutosRequestDto produto) {
        Produtos produtoPersit = this.produtosRequestDtoParaProduto(produto);

        return produtosRepositorio.save(produtoPersit);
    }

    public Produtos atualizar(Long id, ProdutosRequestDto produto) {
        if(produtosRepositorio.existsById(id)) {
            Produtos produtoPersit = this.produtosRequestDtoParaProduto(produto);
            produtoPersit.setId(id);

            return produtosRepositorio.save(produtoPersit);
        }

        throw new RuntimeException("Produto não encontrada!");
    }

    public void deletar(Long id) {
        if(produtosRepositorio.existsById(id)) {
            produtosRepositorio.deleteById(id);
            return;
        }

        throw new RuntimeException("Produto não encontrada!");
    }

    public Produtos listarPorId(Long id) {
        Optional<Produtos> produtoRetorno = produtosRepositorio.findById(id);
        if(produtoRetorno.isPresent()) {
            return produtoRetorno.get();
        }

        throw new RuntimeException("Produto não encontrada!");
    }

    private Produtos produtosRequestDtoParaProduto
            (ProdutosRequestDto entrada) {
        Produtos saida = new Produtos();
        saida.setNome(entrada.getNome());
        saida.setDescricao(entrada.getDescricao());
        saida.setMarca(marcasService.listarPorId(entrada.getMarcaId()));

        return saida;
    }
}
