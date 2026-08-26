package biblioteca;

import biblioteca.gerenciador.Gerenciador;
import biblioteca.livros.Livro;
import biblioteca.usuarios.Usuario;

public class Main {

    public static void main(String[] args) {

        Gerenciador biblioteca = new Gerenciador();

        System.out.println("\n========== Cadastro de Livros ==========");
        biblioteca.adicionar("Duna", "Frank Herbert", 1965);
        biblioteca.adicionar("Neuromancer", "William Gibson", 1984);
        biblioteca.adicionar("Fundação", "Isaac Asimov", 1951);
        biblioteca.adicionar("Andróides Sonham com Ovelhas Elétricas?", "Philip K. Dick", 1968);
        biblioteca.adicionar("2001: Uma Odisséia no Espaço", "Arthur C. Clarke", 1968);

        biblioteca.ordenarLivros();
        biblioteca.exibirLivros();

        Usuario usuario = new Usuario("Marcio");

        String[] emprestimo = {"1984", "Duna"};
        for (String titulo : emprestimo) {
            Livro livro = biblioteca.buscarLivro(titulo);

            if (livro == null) {
                System.out.println("\n[Erro] Livro '" + titulo + "' nao encontrado para emprestimo.");
            } else {
                biblioteca.emprestar(livro, usuario);
            }
        }

        String[] devolucao = {"Duna", "Mitologia Soviética"};
        for (String titulo : devolucao) {
            Livro livro = biblioteca.buscarLivro(titulo);

            if (livro == null) {
                System.out.println("\n[Erro] Livro '" + titulo + "' nao encontrado para devolucao.");
            } else {
                biblioteca.devolver(livro);
            }
        }
    }
}
