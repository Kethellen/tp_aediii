package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Autor implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String nacionalidade;
    private LocalDate dataNascimento;

    public Autor(int id, String nome, String nacionalidade, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getNacionalidade() { return nacionalidade; }
    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setNome(String nome) { this.nome = nome; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    @Override
    public String toString() {
        return "Autor [id=" + id + ", nome=" + nome + ", nacionalidade=" + nacionalidade 
                + ", dataNascimento=" + dataNascimento + "]";
    }
}
