package biblioteca.servico;


import biblioteca.excecao.EntidadeNaoEncontradaException;
import biblioteca.excecao.UsuarioBloqueadoException;
import biblioteca.modelo.Emprestimo;
import biblioteca.modelo.Livro;
import biblioteca.modelo.Usuario;
import biblioteca.repositorio.Repositorio;

import java.time.LocalDate;


public class BibliotecaService {
    private static final int LIMITE_EMPRESTIMOS = 3;
    private static final int IDADE_MINIMA = 12;
    private static final int PRAZO_DIAS = 14;

    private final Repositorio<Emprestimo, Integer> emprestimos;
    private final Repositorio<Livro, Integer> livros;
    private final Repositorio<Usuario, Integer> usuarios;

    public BibliotecaService(Repositorio<Emprestimo, Integer> emprestimo, Repositorio<Livro, Integer> livro, Repositorio<Usuario, Integer> usuario) {
        this.emprestimos = emprestimo;
        this.livros = livro;
        this.usuarios = usuario;

    }


    public void emprestarLivro(int idLivro, int idUsuario, LocalDate hoje) throws EntidadeNaoEncontradaException, UsuarioBloqueadoException {
        Livro livro = livros.buscarPorId(idLivro)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Livro não encontrado: " + idLivro));
        Usuario usuario = usuarios.buscarPorId(idUsuario)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado: " + idUsuario));
        validarMultaPendente(usuario);
        validarLimite(usuario);
        validarIdade(usuario, hoje);




    }

    private void validarLimite(Usuario usuario) throws UsuarioBloqueadoException {
        long count = emprestimos.listarTodos().stream().filter(Emprestimo::estaAtivo).filter(e -> e.getUsuario().equals(usuario)).count();

        if (count >= LIMITE_EMPRESTIMOS) {
            throw new UsuarioBloqueadoException("limite de " + LIMITE_EMPRESTIMOS + " empréstimos ativos atingido");
        }
    }


    private void validarMultaPendente(Usuario usuario) throws UsuarioBloqueadoException {
        if (usuario.isMultaPendente()) {
            throw new UsuarioBloqueadoException("O usuario tem multas pendentes");
        }
    }

    private void validarIdade(Usuario usuario, LocalDate hoje) throws UsuarioBloqueadoException {
        if (usuario.idadeEm(hoje) < IDADE_MINIMA) {
            throw new UsuarioBloqueadoException("Idade mínima: " + IDADE_MINIMA + " anos");
        }
    }


}
