package biblioteca.modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private LocalDate dataNasc;

    public Usuario(String nome, String email, LocalDate dataNasc) {
        if(nome == null || nome.isEmpty() || email == null || email.isEmpty() || dataNasc == null || dataNasc.getYear() < 0){
            throw new IllegalArgumentException("Valores devem ser preenchidos");
        }
        if(!email.contains("@")) throw new IllegalArgumentException("Email invalido");
        this.nome = nome;
        this.email = email;
        this.dataNasc = dataNasc;
    }
}
