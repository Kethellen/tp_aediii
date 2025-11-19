package dao;

import model.Usuario;

public class UsuarioDAO {
    private Arquivo<Usuario> arqUser;
    private ArquivoImagem arqImg;

    public UsuarioDAO() throws Exception {
        arqUser = new Arquivo<>("Usuario", Usuario.class.getConstructor());
        arqImg = new ArquivoImagem("imagemUsuario");
    }

    // getters para acesso
    public Arquivo<Usuario> getArquivoUsuario() { return arqUser; }
    public ArquivoImagem getArquivoImagem() { return arqImg; }

    public Usuario buscarUser(int id) throws Exception {
        return arqUser.read(id);
    }

    public Usuario buscarUsuarioNick(String nick) throws Exception {
        return arqUser.buscarUsuarioNick(nick);
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

    public byte[] buscarFoto(long offset) throws Exception {
        return arqImg.read(offset);
    }

    public long alterarFoto(long offsetAntigo, byte[] novosBytes) throws Exception {
        return arqImg.update(offsetAntigo, novosBytes);
    }

    public long incluirFoto(byte[] bytes) throws Exception {
        return arqImg.create(bytes);
    }

    public boolean excluirFoto(long offset) throws Exception{
        return arqImg.delete(offset);
    }

    public Usuario login(String email, String senha) throws Exception {       
        return arqUser.buscarUsuario(email, senha); 
    }
}
