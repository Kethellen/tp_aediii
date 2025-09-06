package dao;

import model.Autor;

public class AutorDAO {
    private Arquivo<Autor> arqAutor;

    public AutorDAO() throws Exception {
        arqAutor = new Arquivo<>("autor", Autor.class.getConstructor());
    }

    public Autor buscarAutor(int id) throws Exception {
        return arqAutor.read(id);
    }

    public boolean incluirAutor(Autor autor) throws Exception {
        return arqAutor.create(autor) > 0;
    }

    public boolean alterarAutor(Autor autor) throws Exception {
        return arqAutor.update(autor);
    }

    public boolean excluirAutor(int id) throws Exception {
        return arqAutor.delete(id);
    }
}
