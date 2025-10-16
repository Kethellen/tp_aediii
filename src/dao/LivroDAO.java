package dao;

import model.Livro;

import java.util.ArrayList;

import indices.*;

public class LivroDAO {

    private Arquivo<Livro> arqLivro;
    private HashExtensivel<ParIDEndereco> hashIdLivro;
    private HashExtensivel<ParIDEditoraLivros> hashEditoraLivros;

    public LivroDAO() throws Exception {
        arqLivro = new Arquivo<>("Livro", Livro.class.getConstructor());

        hashIdLivro = new HashExtensivel<>(ParIDEndereco.class.getConstructor(), 4, "./dados/hashIdLivroDiretorio.hash_d.db", "./dados/hashIdLivroCestos.hash_c.db");

        hashEditoraLivros = new HashExtensivel<>(
            ParIDEditoraLivros.class.getConstructor(),4,"./dados/hashEditoraLivrosDiretorio.hash_d.db","./dados/hashEditoraLivrosCestos.hash_c.db");
    }

    public Livro buscarLivro(int id) throws Exception {
        ParIDEndereco par = hashIdLivro.read(id);
        if(par == null) { return null; }

        return arqLivro.readByEndereco(par.getEndereco());
    }

    public Livro[] buscaLivrosPorEditora(int idEditora) throws Exception {
        ParIDEditoraLivros par = hashEditoraLivros.read(idEditora);
        if(par == null) { return new Livro[0];}

        ArrayList<Integer> ids = par.getListaLivros();
        Livro[] livros = new Livro[ids.size()];

        for(int i=0; i<ids.size(); i++){
            livros[i] = buscarLivro(ids.get(i));
        }

        return livros;
    }

    public boolean incluirLivro(Livro livro) throws Exception {
        long end = arqLivro.createEndereco(livro);
        if(end < 0) { return false; }

        ParIDEndereco par = new ParIDEndereco(livro.getId(), end);
        hashIdLivro.create(par);

        ParIDEditoraLivros par2 = hashEditoraLivros.read(livro.getIdEditora());
        //System.out.println(par2);

        if(par2 == null){
            par2 = new ParIDEditoraLivros(livro.getIdEditora());
            par2.addLivro(livro.getId());
            hashEditoraLivros.create(par2);

            //ParIDEditoraLivros teste = hashEditoraLivros.read(livro.getIdEditora());
            //System.out.println("Teste: " + teste);
        } else {
            par2.addLivro(livro.getId());
            hashEditoraLivros.update(par2);
        }
        
        return true;
    }

    public boolean alterarLivro(Livro Livro) throws Exception {
        return arqLivro.update(Livro);
    }

    public boolean excluirLivro(int id) throws Exception {
        Livro livro = buscarLivro(id);
        if(livro == null) { return false; }

        boolean deletado = arqLivro.delete(id);
        if(!deletado) { return false; }

        hashIdLivro.delete(id);

        ParIDEditoraLivros par = hashEditoraLivros.read(livro.getIdEditora());
        if(par != null){
            par.removeLivro(id);
            hashEditoraLivros.update(par);
        }

        return true;
    }
}
