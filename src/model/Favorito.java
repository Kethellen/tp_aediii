package model;

import java.io.*;

import dao.Registro;

public class Favorito implements Registro {
    private int id;
    private int idUser;
    private String isbn;
    private String nome;

    public Favorito(){
        this(null, -1, null);
    }

    public Favorito(String nome, int idUser, String isbn){
        this(-1, 1, isbn, nome);
    }

    public Favorito(int id, int idUser, String isbn, String nome) {
        this.id = id;
        this.idUser = idUser;
        this.isbn = isbn;
        this.nome = nome;
    }

    // Getters e Setters
    public int getId() { return id; }
    public int getIdUser() { return idUser; }
    public String getIsbn() { return isbn; }
    public String getNome() { return nome; }
    
    public void setId(int id) { this.id = id; }
    public void setIdUser(int idUser) { this.idUser = idUser; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setNome(String nome) { this.nome = nome; }

    // Implementação do método toByteArray()
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.id);
        dos.writeInt(this.idUser);
        dos.writeUTF(this.isbn);
        dos.writeUTF(this.nome);
        return baos.toByteArray();
    }

    // Implementação do método fromByteArray()
    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.idUser = dis.readInt();
        this.isbn = dis.readUTF();
        this.nome = dis.readUTF();
    }


    @Override
    public String toString() {
        return "Favorito [id="+ id + " idUser=" + idUser + " isbn=" + isbn +"]";
    }
}
