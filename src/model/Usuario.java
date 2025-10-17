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
    private String foto;
    

    public Usuario(){
        this(-1,null, null, null,LocalDate.now(), -1, null, null,null);
    }

    public Usuario(String nome, String nick, String nacionalidade, LocalDate dataNascimento, int idade, String telefone, String email, String foto){
        this(-1, nome, nick, nacionalidade, dataNascimento, idade, telefone,email,foto);
    }

    public Usuario(int idUser, String nome, String nick, String nacionalidade, LocalDate dataNascimento, int idade, String telefone, String email, String foto) {
        this.idUser = idUser;
        this.nome = nome;
        this.nick = nick;
        this.nacionalidade = nacionalidade;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.telefone = telefone;
        this.email = email;
        this.foto = foto;
    }

    @Override
    public Usuario clone() {
        return new Usuario(
            this.idUser,
            this.nome,
            this.nick,
            this.nacionalidade,
            this.dataNascimento,
            this.idade,
            this.telefone,
            this.email,
            this.foto
        );
    }

    // Getters e Setters
    public int getId() { return idUser; }
    public String getNome() { return nome; }
    public String getNick() { return nick; }
    public String getNacionalidade() { return nacionalidade; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public int getIdade() { return idade; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public String getFoto(){ return foto;}

    public void setId(int idUser) { this.idUser = idUser; }
    public void setNome(String nome) { this.nome = nome; }
    public void setNick(String nick) { this.nick = nick; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email) { this.email = email; }
    public void getFoto(String foto){ this.foto = foto;}

    // Implementação do método toByteArray()
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.idUser);
        dos.writeUTF(this.nome);
        dos.writeUTF(this.nick);
        dos.writeUTF(this.nacionalidade);
        dos.writeLong(this.dataNascimento.toEpochDay());
        dos.writeInt(this.idade);
        dos.writeUTF(this.telefone);
        dos.writeUTF(this.email);
        dos.writeUTF(this.foto);
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
        this.idade = dis.readInt();
        this.telefone = dis.readUTF();
        this.email = dis.readUTF();
        this.foto = dis.readUTF();
    }


    @Override
    public String toString() {
        return "Usuario [id=" + idUser + ", nome=" + nome + ", nick=" + nick + ", nacionalidade=" + nacionalidade 
                + ", dataNascimento=" + dataNascimento + "Idade=" + idade + "telefone=" + telefone+ "email="+ email +"foto=" + foto+"]";
    }
}
