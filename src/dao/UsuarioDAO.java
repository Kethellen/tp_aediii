package dao;

import model.Usuario;

public class UsuarioDAO {
    private Arquivo<Usuario> arqUser;

    public UsuarioDAO() throws Exception {
        arqUser = new Arquivo<>("Usuario", Usuario.class.getConstructor());
    }

    public Usuario buscarUser(int id) throws Exception {
        return arqUser.read(id);
    }

    public int incluirUser(Usuario user) throws Exception {
        return arqUser.create(user);
    }

    public boolean alterarUser(Usuario user) throws Exception {
        return arqUser.update(user);
    }

    public boolean excluirUser(int id) throws Exception {
        return arqUser.delete(id);
    }
}
