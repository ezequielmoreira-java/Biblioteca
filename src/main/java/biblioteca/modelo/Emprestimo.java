package biblioteca.modelo;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class Emprestimo {
    private int id;
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;


    public Emprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataPrevistaDevolucao, int id) {
        if (livro.titulo().isEmpty() || dataEmprestimo == null || dataPrevistaDevolucao == null|| id < 0) throw new IllegalArgumentException("Valores invalido");
        this.id = id;
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public Optional<LocalDate> getDataDevolucao() {
        return Optional.ofNullable(this.dataPrevistaDevolucao);
    }
    public boolean estaAtivo(){
        return getDataDevolucao().isEmpty();
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(livro.id(), that.livro.id());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(livro.id());
    }


}
