package indices;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParIDEndereco implements RegistroHashExtensivel<ParIDEndereco> {
    
    private int idLivro;   // chave
    private long enderecoLivro;    // valor
    private final short TAMANHO = 12;  // tamanho em bytes

    public ParIDEndereco() {
        this.idLivro = -1;
        this.enderecoLivro = -1;
    }

    public ParIDEndereco(int idLivro, long end) {
        this.idLivro = idLivro;
        this.enderecoLivro = end;
    }

    public int getId() {
        return idLivro;
    }

    public long getEndereco() {
        return enderecoLivro;
    }

    @Override
    public int hashCode() {
        return this.idLivro;
    }

    public short size() {
        return this.TAMANHO;
    }

    public String toString() {
        return "IdLivro: " + idLivro + " -> EndereçoLivro: " + enderecoLivro;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.idLivro);
        dos.writeLong(this.enderecoLivro);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.idLivro = dis.readInt();
        this.enderecoLivro = dis.readLong();
    }

}