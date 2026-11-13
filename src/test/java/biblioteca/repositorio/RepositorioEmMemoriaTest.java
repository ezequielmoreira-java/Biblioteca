package biblioteca.repositorio;

import biblioteca.modelo.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RepositorioEmMemoriaTest {

    private Repositorio<Livro, Integer> repositorio;

    @BeforeEach
    void preparar() {
        repositorio = new RepositorioEmMemoria<>(Livro::id);
    }

    @Test
    void deveBuscarLivroSalvo() {
        Livro livro = new Livro(1, "Clean Code", "Robert Martin", 2008);
        repositorio.salvar(livro);

        var resultado = repositorio.buscarPorId(1);

        assertTrue(resultado.isPresent());
        assertEquals("Clean Code", resultado.get().titulo());
    }

    @Test
    void deveDevolverVazioParaIdInexistente() {
        assertTrue(repositorio.buscarPorId(99).isEmpty());
    }

    @Test
    void deveRemoverUmValor() {
        Livro livro = new Livro(1, "Clean Code", "Robert Martin", 2008);
        repositorio.salvar(livro);

        Optional<Livro> removido = repositorio.remover(1);

        assertTrue(removido.isPresent());
        assertEquals(livro, removido.get());
        assertTrue(repositorio.buscarPorId(1).isEmpty());
    }

    @Test
    void deveDevolverVazioAoRemoverIdInexistente() {
        assertTrue(repositorio.remover(99).isEmpty());
    }

}