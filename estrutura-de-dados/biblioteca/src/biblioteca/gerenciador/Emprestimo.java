package biblioteca.gerenciador;

import biblioteca.livros.Livro;
import biblioteca.usuarios.Usuario;

public interface Emprestimo {

    void emprestar(Livro livro, Usuario usuario);
    void devolver(Livro livro);

}
