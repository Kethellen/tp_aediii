package model;

import java.io.*;
import java.time.LocalDate;

import dao.Registro;

public class Livro implements Registro {
    private int id;
    private String isbn;
    private String titulo;
    private String genero;
    private String edicao;
    private String prefixo;
    private String paisOrigem;
    private LocalDate dataPublicacao;
    private int idEditora;

    public Livro(){
        this(-1,null,null,null,null,null,null,LocalDate.now(),-1);
    }

    public Livro(String isbn, String titulo, String genero, String edicao, String prefixo, String paisOrigem, LocalDate dataPublicacao, int idEditora){
        this(-1, isbn, titulo, genero, prefixo, edicao, paisOrigem, dataPublicacao, idEditora);
    }

    public Livro(int id, String isbn, String titulo, String genero, String edicao, String prefixo, String paisOrigem, LocalDate dataPublicacao, int idEditora) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.genero = genero;
        this.edicao = edicao;
        this.prefixo = prefixo;
        this.paisOrigem = paisOrigem;
        this.dataPublicacao = dataPublicacao;
        this.idEditora = idEditora;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public String getEdicao() { return edicao; }
    public String getPrefixo() { return prefixo; }
    public String getPaisOrigem() { return paisOrigem; }
    public LocalDate getDataPublicacao() { return dataPublicacao; }
    public int getIdEditora() { return idEditora; }

    public void setId(int id) { this.id = id; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setEdicao(String edicao) { this.edicao = edicao; }
    public void setPrefixo(String prefixo) { this.prefixo = prefixo; }
    public void setPaisOrigem(String paisOrigem) { this.paisOrigem = paisOrigem; }
    public void setDataPublicacao(LocalDate dataPublicacao) { this.dataPublicacao = dataPublicacao; }
    public void setIdEditora(int idEditora) { this.idEditora = idEditora; }

    // Implementação do método toByteArray()
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.id);
        dos.writeUTF(this.isbn);
        dos.writeUTF(this.titulo);
        dos.writeUTF(this.genero);
        dos.writeUTF(this.edicao);
        dos.writeUTF(this.prefixo);
        dos.writeUTF(this.paisOrigem);
        dos.writeLong(this.dataPublicacao.toEpochDay());
        dos.writeInt(this.idEditora);
        return baos.toByteArray();
    }

    // Implementação do método fromByteArray()
    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.isbn = dis.readUTF();
        this.titulo = dis.readUTF();
        this.genero = dis.readUTF();
        this.edicao = dis.readUTF();
        this.prefixo = dis.readUTF();
        this.paisOrigem = dis.readUTF();
        this.dataPublicacao = LocalDate.ofEpochDay(dis.readLong());
        this.idEditora = dis.readInt();
    }


    @Override
    public String toString() {
        return "Livro [id=" + id + ", ISBN=" + isbn + ", titulo=" + titulo 
                + ", genero=" + genero + ", edicao=" + edicao + ", prefixo=" 
                + prefixo + ", paisOrigem=" + paisOrigem + ", dataPublicacao=" 
                + dataPublicacao + ", idEditora=" + idEditora + "]";
    }
}
