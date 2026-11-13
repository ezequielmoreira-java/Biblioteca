package biblioteca.modelo;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private LocalDate dataNasc;
    private boolean multaPedente;

    public Usuario(int id,String nome, String email, LocalDate dataNasc) {
        if(nome == null || nome.isEmpty() || email == null || email.isEmpty() || dataNasc == null || dataNasc.getYear() < 0|| id < 0){
            throw new IllegalArgumentException("Valores devem ser preenchidos");
        }
        if(!email.contains("@")) throw new IllegalArgumentException("Email invalido");
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNasc = dataNasc;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public int getId() {
        return id;
    }

    public int idadeEm(LocalDate referencia) {
        return Period.between(dataNasc, referencia).getYears();
    }

    public boolean isMultaPendente() {
        return multaPedente;
    }

   public void registarMulta() {
        this.multaPedente = true;
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
