/*
Esta classe representa um objeto para uma entidade
que será armazenado em uma árvore B+

Neste caso em particular, este objeto é representado
por dois números inteiros que representam o relacionamento
N:N entre Livro e Autor.

Adaptado do Prof. Marcos Kutova
v1.0 - 2024
Estante Digital - AEDS III
*/

package indices;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParLivroAutor implements RegistroArvoreBMais<ParLivroAutor> {

    private int idLivro;
    private int idAutor;
    private short TAMANHO = 8;

    public ParLivroAutor() {
        this(-1, -1);
    }

    public ParLivroAutor(int idLivro) {
        this(idLivro, -1);
    }

    public ParLivroAutor(int idLivro, int idAutor) {
        try {
            this.idLivro = idLivro; // ID do Livro
            this.idAutor = idAutor; // ID do Autor
        } catch (Exception ec) {
            ec.printStackTrace();
        }
    }

    public int getIdLivro() {
        return idLivro;
    }

    public int getIdAutor() {
        return idAutor;
    }

    @Override
    public ParLivroAutor clone() {
        return new ParLivroAutor(this.idLivro, this.idAutor);
    }

    public short size() {
        return this.TAMANHO;
    }

    public int compareTo(ParLivroAutor a) {
        if (this.idLivro != a.idLivro)
            return this.idLivro - a.idLivro;
        else
            // Só compara os valores de idAutor, se o idAutor da busca for diferente de -1
            // Isso é necessário para que seja possível a busca de lista
            return this.idAutor == -1 ? 0 : this.idAutor - a.idAutor;
    }

    public String toString() {
        return String.format("%3d", this.idLivro) + ";" + String.format("%-3d", this.idAutor);
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.idLivro);
        dos.writeInt(this.idAutor);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.idLivro = dis.readInt();
        this.idAutor = dis.readInt();
    }
}
