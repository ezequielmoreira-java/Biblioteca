package biblioteca.servico;


import biblioteca.excecao.EntidadeNaoEncontradaException;
import biblioteca.excecao.UsuarioBloqueadoException;
import biblioteca.modelo.Emprestimo;
import biblioteca.modelo.Livro;
import biblioteca.modelo.Usuario;
import biblioteca.repositorio.Repositorio;

import java.time.LocalDate;

public class BibliotecaService {
    private final Repositorio<Emprestimo, Integer> emprestimo;
    private final Repositorio<Livro, Integer> livro;
    private final Repositorio<Usuario, Integer> usuario;

    public BibliotecaService(Repositorio<Emprestimo, Integer> emprestimo, Repositorio<Livro, Integer> livro, Repositorio<Usuario, Integer> usuario) {
        this.emprestimo = emprestimo;
        this.livro = livro;
        this.usuario = usuario;

    }


}
