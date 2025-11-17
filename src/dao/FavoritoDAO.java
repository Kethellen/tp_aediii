package dao;

import java.util.ArrayList;

import model.Favorito;

public class FavoritoDAO {
    private Arquivo<Favorito> arqFavorito;

    public FavoritoDAO() throws Exception {
        arqFavorito = new Arquivo<>("Favorito", Favorito.class.getConstructor());
    }

    public Favorito buscarFavorito(int id) throws Exception {
        return arqFavorito.read(id);
    }
    public ArrayList<Favorito> listarPorUsuario(int idUser) throws Exception {
        return arqFavorito.scan(f -> ((Favorito) f).getIdUser() == idUser);
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
