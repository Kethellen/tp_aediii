package model;

import java.io.*;
import java.time.LocalDate;

import dao.Registro;

public class Usuario implements Registro {
    private int idUser;
    private String nome;
    private String nick;
    private LocalDate dataNascimento;
    private int idade;
    private String email;
    private String nacionalidade;
    private String telefone;
    private long fotoOffset;
    private int  fotoLength;
    private String tipo;
    private String senha;
    

    public Usuario(){
        this(-1,null, null, null,LocalDate.now(), null, null, -1L, 0, null, null);
    }

    public Usuario(String nome, String nick, String nacionalidade, LocalDate dataNascimento, String telefone, String email, Long fotoOffset, int fotoLength, String tipo, String senha){
        this(-1, nome, nick, nacionalidade, dataNascimento, telefone,email,fotoOffset,fotoLength, tipo, senha);
    }

    public Usuario(int idUser, String nome, String nick, String nacionalidade, LocalDate dataNascimento, String telefone, String email, Long fotoOffset, int fotoLength, String tipo, String senha) {
        this.idUser = idUser;
        this.nome = nome;
        this.nick = nick;
        this.nacionalidade = nacionalidade;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.fotoOffset = fotoOffset;
        this.fotoLength = fotoLength;
        this.tipo = tipo;
        this.senha = senha;
    }

    @Override
    public Usuario clone() {
        return new Usuario(
            this.idUser,
            this.nome,
            this.nick,
            this.nacionalidade,
            this.dataNascimento,
            this.telefone,
            this.email,
            this.fotoOffset,
            this.fotoLength,
            this.tipo,
            this.senha
        );
    }

    // Getters e Setters
    public int getId() { return idUser; }
    public String getNome() { return nome; }
    public String getNick() { return nick; }
    public String getNacionalidade() { return nacionalidade; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public Long getFotoOffset(){ return fotoOffset;}
    public int getFotoLength(){ return fotoLength;}
    public String getTipo(){ return tipo;}
    public String getSenha(){ return senha;}

    public void setId(int idUser) { this.idUser = idUser; }
    public void setNome(String nome) { this.nome = nome; }
    public void setNick(String nick) { this.nick = nick; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email) { this.email = email; }
    public void setFotoOffset(Long fotoOffset){ this.fotoOffset = fotoOffset;}
    public void setFotoLength(int fotoLength){ this.fotoLength = fotoLength;}
    public void setTipo(String tipo){ this.tipo = tipo;}
    public void setSenha(String senha){ this.senha = senha;}

    // Implementação do método toByteArray()
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.idUser);
        dos.writeUTF(this.nome);
        dos.writeUTF(this.nick);
        dos.writeUTF(this.nacionalidade);
        dos.writeLong(this.dataNascimento.toEpochDay());
        dos.writeUTF(this.telefone);
        dos.writeUTF(this.email);
        dos.writeLong(fotoOffset);
        dos.writeInt(fotoLength);
        dos.writeUTF(this.tipo);
        dos.writeUTF(this.senha);
        return baos.toByteArray();
    }

    // Implementação do método fromByteArray()
    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.idUser = dis.readInt();
        this.nome = dis.readUTF();
        this.nick = dis.readUTF();
        this.nacionalidade = dis.readUTF();
        this.dataNascimento = LocalDate.ofEpochDay(dis.readLong());
        this.telefone = dis.readUTF();
        this.email = dis.readUTF();
        this.fotoOffset = dis.readLong();
        this.fotoLength = dis.readInt();
        this.tipo = dis.readUTF();
        this.senha = dis.readUTF();
    }


    @Override
    public String toString() {
        return "Usuario [id=" + idUser + ", nome=" + nome + ", nick=" + nick + ", nacionalidade=" + nacionalidade 
                + ", dataNascimento=" + dataNascimento + "Idade=" + idade + "telefone=" + telefone+ "email="+ email +"TamanhoFoto=" + fotoLength + "Tipo="+ tipo +"]";
    }
}
