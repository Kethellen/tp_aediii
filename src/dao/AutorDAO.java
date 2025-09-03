package dao;

import model.Autor;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    private static final String FILE_NAME = "autores.dat";

    // Salvar lista de autores
    private void salvar(List<Autor> autores) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(autores);
        }
    }

    // Carregar lista de autores
    @SuppressWarnings("unchecked")
    private List<Autor> carregar() throws IOException, ClassNotFoundException {
        File arquivo= new File(FILE_NAME);
        if (!arquivo.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Autor>) in.readObject();
        }
    }

    // CRUD -------------------

    public void criar(Autor autor) throws IOException, ClassNotFoundException {
        List<Autor> autores = carregar();
        autores.add(autor);
        salvar(autores);
    }

    public List<Autor> listar() throws IOException, ClassNotFoundException {
        return carregar();
    }

    public Autor buscarPorNome(String nome) throws IOException, ClassNotFoundException {
        for (Autor a : carregar()) {
            if (a.getNome() == nome) return a;
        }
        return null;
    }

    public void atualizar(Autor autorAtualizado) throws IOException, ClassNotFoundException {
        List<Autor> autores = carregar();
        for (int i = 0; i < autores.size(); i++) {
            if (autores.get(i).getId() == autorAtualizado.getId()) {
                autores.set(i, autorAtualizado);
                salvar(autores);
                return;
            }
        }
    }

    public void deletar(int id) throws IOException, ClassNotFoundException {
        List<Autor> autores = carregar();
        autores.removeIf(a -> a.getId() == id);
        salvar(autores);
    }
}
