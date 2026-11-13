package biblioteca.servico;

import biblioteca.excecao.*;
import biblioteca.modelo.*;
import biblioteca.repositorio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecaServiceTest {

    private static final LocalDate HOJE = LocalDate.of(2026, 11, 12);

    private Repositorio<Livro, Integer> livros;
    private Repositorio<Usuario, Integer> usuarios;
    private Repositorio<Emprestimo, Integer> emprestimos;
    private BibliotecaService servico;

    @BeforeEach
    void preparar() {
        livros = new RepositorioEmMemoria<>(Livro::id);
        usuarios = new RepositorioEmMemoria<>(Usuario::getId);
        emprestimos = new RepositorioEmMemoria<>(Emprestimo::getId);
        servico = new BibliotecaService(emprestimos, livros, usuarios);
    }


    @Test
    void deveFalharQuandoLivroNaoExiste(){
        usuarios.salvar(new Usuario(1, "Ana", "ana@mail.com", LocalDate.of(2000, 1, 1)));
        assertThrows(EntidadeNaoEncontradaException.class,
                () -> servico.emprestarLivro(99, 1, HOJE));
    }


    @Test
    void deveFalharQuandoUmUsuarioNaoExiste(){
        livros.salvar(new Livro(10,"Senhor dos Aneis","J. R. R. Tolkien",1954));
        assertThrows(EntidadeNaoEncontradaException.class,
                () -> servico.emprestarLivro(10, 1, HOJE));
    }
    @Test
    void deveBloquearUsuarioComMultaPendente(){
        Usuario ana = new Usuario(1, "Ana", "ana@mail.com", LocalDate.of(2000, 1, 1));
        ana.registarMulta();
        usuarios.salvar(ana);
        livros.salvar(new Livro(10,"Senhor dos Aneis","J. R. R. Tolkien ",1954));
        UsuarioBloqueadoException e = assertThrows(UsuarioBloqueadoException.class,
                () -> servico.emprestarLivro(10, 1, HOJE));
        assertTrue(e.getMessage().contains("multa"));


       assertThrows( UsuarioBloqueadoException.class,
               () -> servico.emprestarLivro(10, 1, HOJE));
    }
    @Test
    void deveBloquearUsuarioQueFaz12AnosAmanha() {
        Usuario caio = new Usuario(1, "Caio", "caio@mail.com", HOJE.minusYears(12).plusDays(1));
        usuarios.salvar(caio);
        livros.salvar(new Livro(10, "Senhor dos Aneis", "J. R. R. Tolkien", 1954));

        UsuarioBloqueadoException e = assertThrows(UsuarioBloqueadoException.class,
                () -> servico.emprestarLivro(10, 1, HOJE));

        assertTrue(e.getMessage().contains("Idade"));
        assertTrue(emprestimos.listarTodos().isEmpty());
    }

    @Test
    void devePermitirUsuarioQueFaz12AnosHoje() {
        Usuario caio = new Usuario(1, "Caio", "caio@mail.com", HOJE.minusYears(12));
        usuarios.salvar(caio);
        livros.salvar(new Livro(10, "Senhor dos Aneis", "J. R. R. Tolkien", 1954));

        assertDoesNotThrow(() -> servico.emprestarLivro(10, 1, HOJE));
    }

    @Test
    void devePermitirUsuarioQueFez12AnosOntem() {
        Usuario caio = new Usuario(1, "Caio", "caio@mail.com", HOJE.minusYears(12).minusDays(1));
        usuarios.salvar(caio);
        livros.salvar(new Livro(10, "Senhor dos Aneis", "J. R. R. Tolkien", 1954));

        assertDoesNotThrow(() -> servico.emprestarLivro(10, 1, HOJE));
    }
//    @Test
     //Aguardado o metodo emprestarLivros ficar completo
//    void deveBloquearUsuarioCom3EmprestivosAtivos(){
//        Usuario caio = new Usuario(1, "Caio", "caio@mail.com",LocalDate.of(2000, 1, 1));
//        usuarios.salvar(caio);
//        livros.salvar(new Livro(10, "Senhor dos Aneis", "J. R. R. Tolkien", 1954));
//        livros.salvar(new Livro(11, "1984", "George Orwell", 1949));
//        livros.salvar(new Livro(12, "Duna", "Frank Herbert", 1965));
//        livros.salvar(new Livro(13, "O Hobbit", "J. R. R. Tolkien", 1937));
//
//        assertDoesNotThrow(() -> servico.emprestarLivro(10, 1, HOJE));
//        assertDoesNotThrow(() -> servico.emprestarLivro(11, 1, HOJE));
//        assertDoesNotThrow(() -> servico.emprestarLivro(12, 1, HOJE));
//
//        UsuarioBloqueadoException e = assertThrows(UsuarioBloqueadoException.class,
//                () -> servico.emprestarLivro(13, 1, HOJE));
//        assertTrue(e.getMessage().contains("limite"));
//
//
//    }






    }





