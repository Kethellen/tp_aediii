package indices;

import java.io.*;
import java.util.ArrayList;

public class ParIDEditoraLivros implements RegistroHashExtensivel<ParIDEditoraLivros> {
    private int idEditora;
    private ArrayList<Integer> listaLivros;
    private final short TAMANHO = 512; // tamanho fixo, ajustável

    public ParIDEditoraLivros() {
        this.idEditora = -1;
        this.listaLivros = new ArrayList<>();
    }

    public ParIDEditoraLivros(int idEditora) {
        this.idEditora = idEditora;
        this.listaLivros = new ArrayList<>();
    }

    public int getId(){
        return idEditora;
    }

    public ArrayList<Integer> getListaLivros(){
        return listaLivros;
    }

    @Override
    public int hashCode() { return this.idEditora; }

    public short size() { return this.TAMANHO; }

    @Override
    public String toString() {
        return "Editora: " + idEditora + " -> Livros: " + listaLivros;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(TAMANHO);
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(idEditora);
        dos.writeInt(listaLivros.size());
        for (int id : listaLivros) dos.writeInt(id);
        // preenche o restante com zeros
        while (baos.size() < TAMANHO) dos.writeByte(0);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(ba));
        idEditora = dis.readInt();
        int tamanho = dis.readInt();
        listaLivros = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) listaLivros.add(dis.readInt());
    }

    public void addLivro(int idLivro) { listaLivros.add(idLivro); }
    public void removeLivro(int idLivro) { listaLivros.remove((Integer) idLivro); }
}
