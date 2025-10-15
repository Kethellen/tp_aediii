package dao;

import model.Editora;

public class EditoraDAO {
    private Arquivo<Editora> arqEditora;

    public EditoraDAO() throws Exception {
        arqEditora = new Arquivo<>("Editora", Editora.class.getConstructor());
    }

    public Editora buscarEditora(int id) throws Exception {
        return arqEditora.read(id);
    }

    public boolean incluirEditora(Editora Editora) throws Exception {
        return arqEditora.create(Editora) > 0;
    }

    public boolean alterarEditora(Editora Editora) throws Exception {
        return arqEditora.update(Editora);
    }

    public boolean excluirEditora(int id) throws Exception {
        return arqEditora.delete(id);
    }
}
