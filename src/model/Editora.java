package model;

import java.io.*;

import dao.Registro;

public class Editora implements Registro {
    private int id;
    private String nome;

    public Editora(){
        this(-1,null);
    }

    public Editora(String nome){
        this(-1, nome);
    }

    public Editora(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }


    // Implementação do método toByteArray()
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.id);
        dos.writeUTF(this.nome);
        return baos.toByteArray();
    }

    // Implementação do método fromByteArray()
    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.nome = dis.readUTF();
    }


    @Override
    public String toString() {
        return "Editora [id=" + id + ", nome=" + nome + "]";
    }
}
