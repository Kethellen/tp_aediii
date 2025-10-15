package dao;

import model.Livro;

public class LivroDAO {
    private Arquivo<Livro> arqLivro;

    public LivroDAO() throws Exception {
        arqLivro = new Arquivo<>("Livro", Livro.class.getConstructor());
    }

    public Livro buscarLivro(int id) throws Exception {
        return arqLivro.read(id);
    }

    public boolean incluirLivro(Livro Livro) throws Exception {
        return arqLivro.create(Livro) > 0;
    }

    public boolean alterarLivro(Livro Livro) throws Exception {
        return arqLivro.update(Livro);
    }

    public boolean excluirLivro(int id) throws Exception {
        return arqLivro.delete(id);
    }
}
