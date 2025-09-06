package model;

import java.io.*;
import java.time.LocalDate;

import dao.Registro;

public class Autor implements Registro {
    private int id;
    private String nome;
    private String nacionalidade;
    private LocalDate dataNascimento;

    public Autor(){
        this(-1,null, null,LocalDate.now());
    }

    public Autor(String nome, String nacionalidade, LocalDate dataNascimento){
        this(-1, nome, nacionalidade, dataNascimento);
    }

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

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    // Implementação do método toByteArray()
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.id);
        dos.writeUTF(this.nome);
        dos.writeUTF(this.nacionalidade);
        dos.writeLong(this.dataNascimento.toEpochDay());
        return baos.toByteArray();
    }

    // Implementação do método fromByteArray()
    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.nome = dis.readUTF();
        this.nacionalidade = dis.readUTF();
        this.dataNascimento = LocalDate.ofEpochDay(dis.readLong());
    }


    @Override
    public String toString() {
        return "Autor [id=" + id + ", nome=" + nome + ", nacionalidade=" + nacionalidade 
                + ", dataNascimento=" + dataNascimento + "]";
    }
}
