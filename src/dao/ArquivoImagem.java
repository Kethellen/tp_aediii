package dao;

import java.io.File;
import java.io.RandomAccessFile;

public class ArquivoImagem {
    private static final int TAM_CABECALHO = 8; // só ponteiro p/ espaços deletados
    private RandomAccessFile raf;
    private String nomeArquivo;

    public ArquivoImagem(String nomeArquivo) throws Exception {
        // Cria estrutura de diretórios
        File dir = new File("./imagem/");
        if (!dir.exists()) dir.mkdirs();

        dir = new File("./imagem/" + nomeArquivo);
        if (!dir.exists()) dir.mkdir();

        this.nomeArquivo = "./imagem/" + nomeArquivo + "/" + nomeArquivo + ".blob";
        this.raf = new RandomAccessFile(this.nomeArquivo, "rw");

        // Cabeçalho: ponteiro p/ lista encadeada de espaços deletados
        if (raf.length() < TAM_CABECALHO) {
            raf.seek(0);
            raf.writeLong(-1); // lista vazia
        }
    }

    // === CREATE ===
    public long create(byte[] bytes) throws Exception {
        long endereco = getDeleted(bytes.length);
        long posicao;

        if (endereco == -1) { // sem espaço reaproveitável
            raf.seek(raf.length());
            posicao = raf.getFilePointer();
            raf.writeByte(' ');
            raf.writeInt(bytes.length);
            raf.write(bytes);
        } else {
            raf.seek(endereco);
            raf.writeByte(' ');
            raf.skipBytes(4); // pula o tamanho antigo
            raf.write(bytes);
            posicao = endereco;
        }

        return posicao; // offset inicial do registro
    }

    // === READ ===
    public byte[] read(long offset) throws Exception {
        raf.seek(offset);
        byte lapide = raf.readByte();
        int tamanho = raf.readInt();

        if (lapide != ' ') throw new Exception("Imagem apagada ou inválida.");
        byte[] dados = new byte[tamanho];
        raf.readFully(dados);
        return dados;
    }

    // === UPDATE ===
    public long update(long offsetAntigo, byte[] novosBytes) throws Exception {
        raf.seek(offsetAntigo);
        byte lapide = raf.readByte();
        int tamanhoAntigo = raf.readInt();

        // se nova imagem cabe no espaço antigo
        if (lapide == ' ' && novosBytes.length <= tamanhoAntigo) {
            raf.seek(offsetAntigo + 5); // pula lápide + tamanho
            raf.write(novosBytes);
            return offsetAntigo;
        } 
        else {
            // marca antigo como excluído
            raf.seek(offsetAntigo);
            raf.writeByte('*');
            addDeleted(tamanhoAntigo, offsetAntigo);

            // grava nova no final (ou em espaço livre)
            return create(novosBytes);
        }
    }

    // === DELETE ===
    public boolean delete(long offset) throws Exception {
        raf.seek(offset);
        byte lapide = raf.readByte();
        int tamanho = raf.readInt();

        if (lapide == ' ') {
            raf.seek(offset);
            raf.writeByte('*');
            addDeleted(tamanho, offset);
            return true;
        }
        return false;
    }

    // === LISTA DE ESPAÇOS DELETADOS 
    private void addDeleted(int tamanhoEspaco, long enderecoEspaco) throws Exception {
        raf.seek(0);
        long inicioLista = raf.readLong();

        raf.seek(enderecoEspaco + 1 + 4); // após lápide + tamanho
        raf.writeLong(inicioLista); // encadeia o próximo

        raf.seek(0);
        raf.writeLong(enderecoEspaco); // novo início da lista
    }

    private long getDeleted(int tamanhoNecessario) throws Exception {
        raf.seek(0);
        long endereco = raf.readLong();
        long anterior = -1;

        while (endereco != -1) {
            raf.seek(endereco + 1); 
            int tamanho = raf.readInt();
            long proximo = raf.readLong();

            if (tamanho >= tamanhoNecessario) {
                if (anterior == -1) {
                    raf.seek(0);
                    raf.writeLong(proximo);
                } else {
                    raf.seek(anterior + 1 + 4);
                    raf.writeLong(proximo);
                }
                return endereco;
            }

            anterior = endereco;
            endereco = proximo;
        }
        return -1;
    }

    public void close() throws Exception {
        raf.close();
    }
}
