package dao;

import model.Favorito;

public class FavoritoDAO {
    private Arquivo<Favorito> arqFavorito;

    public FavoritoDAO() throws Exception {
        arqFavorito = new Arquivo<>("Favorito", Favorito.class.getConstructor());
    }

    public Favorito buscarFavorito(int id) throws Exception {
        return arqFavorito.read(id);
    }

    public int incluirFavorito(Favorito favorito) throws Exception {
        return arqFavorito.create(favorito);
    }

    public boolean alterarFavorito(Favorito favorito) throws Exception {
        return arqFavorito.update(favorito);
    }

    public boolean excluirFavorito(int id) throws Exception {
        return arqFavorito.delete(id);
    }
}
