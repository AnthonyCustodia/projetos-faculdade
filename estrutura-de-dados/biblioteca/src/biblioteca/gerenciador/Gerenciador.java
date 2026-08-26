package biblioteca.gerenciador;

import biblioteca.livros.Livro;
import biblioteca.usuarios.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gerenciador implements Emprestimo {

    private final List<Livro> livrosDisponiveis = new ArrayList<>();
    private final List<Livro> livrosEmprestados = new ArrayList<>();

    public void adicionar(String titulo, String autor, int anoPubli) {
        Livro livro = new Livro(titulo, autor, anoPubli);
        livrosDisponiveis.add(livro);
        System.out.println("Livro '" + titulo + "' cadastrado com sucesso");
    }

    public void ordenarLivros() {
        int n = livrosDisponiveis.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (livrosDisponiveis.get(j).getTitulo().compareToIgnoreCase(
                        livrosDisponiveis.get(j + 1).getTitulo()) > 0) {
                    Livro aux = livrosDisponiveis.get(j);
                    livrosDisponiveis.set(j, livrosDisponiveis.get(j + 1));
                    livrosDisponiveis.set(j + 1, aux);
                }
            }
        }
        System.out.println("\n========================================");
        System.out.println("Livros ordenados por titulo");
    }

    @Override
    public void devolver(Livro livro) {
        if (livrosEmprestados.contains(livro)) {
            livrosEmprestados.remove(livro);
            livrosDisponiveis.add(livro);

            Date dataDevolucao = new Date();

            System.out.println("\n======= Comprovante de Devolucao =======");
            System.out.println("Livro: " + livro.getTitulo());
            System.out.println("Data: " + dataDevolucao);
        } else {
            System.out.println("Livro nao identificado na lista de emprestados");
        }
    }

    @Override
    public void emprestar(Livro livro, Usuario usuario) {
        if (livrosDisponiveis.contains(livro)) {
            livrosDisponiveis.remove(livro);
            livrosEmprestados.add(livro);

            Date dataEmprestimo = new Date();

            System.out.println("\n====== Comprovante de Emprestimo =======");
            System.out.println("Usuario: " + usuario.getNome());
            System.out.println("Livro: " + livro.getTitulo());
            System.out.println("Data: " + dataEmprestimo);
        } else {
            System.out.println("\n== Emprestimo Indisponivel no Momento ==");
            System.out.println("Livro " + livro.getTitulo() + " indisponivel");
        }
    }

    public void exibirLivros() {
        System.out.println("\n============== Biblioteca ==============");
        for (Livro livro : livrosDisponiveis) {
            System.out.println(" - " + livro.getTitulo() + " (" + livro.getAutor() + ", " + livro.getAnoPubli() + ")");
        }
    }

    public Livro buscarLivro(String titulo) {
        for (Livro livro : livrosDisponiveis) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        for (Livro livro : livrosEmprestados) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }
}
