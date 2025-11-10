package dao;

import indices.ParLivroAutor;
import indices.ArvoreBMais;
import dao.AutorDAO;
import dao.LivroDAO;
import model.Autor;
import model.Livro;
import java.util.ArrayList;
import java.io.File;

public class LivroAutorDAO {

    private ArvoreBMais<ParLivroAutor> arvore;

    public LivroAutorDAO() throws Exception {
        File dir = new File("./dados/relacionamentos");
        if (!dir.exists())
            dir.mkdirs();
        this.arvore = new ArvoreBMais<>(ParLivroAutor.class.getConstructor(), 4, 
                                       "./dados/relacionamentos/arvore_livro_autor.db");
    }

    // CREATE - Associar livro a autor
    public boolean associarLivroAutor(int idLivro, int idAutor) throws Exception {
        ParLivroAutor par = new ParLivroAutor(idLivro, idAutor);
        return arvore.create(par);
    }

    // READ - Buscar todos os autores de um livro (apenas IDs)
    public ArrayList<Integer> buscarAutoresDoLivro(int idLivro) throws Exception {
        ArrayList<ParLivroAutor> pares = arvore.read(new ParLivroAutor(idLivro, -1));
        ArrayList<Integer> autores = new ArrayList<>();
        for (ParLivroAutor par : pares) {
            autores.add(par.getIdAutor());
        }
        return autores;
    }

    // READ - Buscar todos os autores de um livro (objetos completos)
    public ArrayList<Autor> buscarAutoresDoLivroCompleto(int idLivro) throws Exception {
        ArrayList<ParLivroAutor> pares = arvore.read(new ParLivroAutor(idLivro, -1));
        ArrayList<Autor> autores = new ArrayList<>();
        
        AutorDAO autorDAO = new AutorDAO();
        for (ParLivroAutor par : pares) {
            Autor autor = autorDAO.buscarAutor(par.getIdAutor());
            if (autor != null) {
                autores.add(autor);
            }
        }
        return autores;
    }

    // READ - Buscar todos os livros de um autor (apenas IDs)
    public ArrayList<Integer> buscarLivrosDoAutor(int idAutor) throws Exception {
        ArrayList<ParLivroAutor> todos = arvore.read(null);
        ArrayList<Integer> livros = new ArrayList<>();
        for (ParLivroAutor par : todos) {
            if (par.getIdAutor() == idAutor) {
                livros.add(par.getIdLivro());
            }
        }
        return livros;
    }

    // READ - Buscar todos os livros de um autor (objetos completos)
    public ArrayList<Livro> buscarLivrosDoAutorCompleto(int idAutor) throws Exception {
        ArrayList<ParLivroAutor> todos = arvore.read(null);
        ArrayList<Livro> livros = new ArrayList<>();
        
        LivroDAO livroDAO = new LivroDAO();
        for (ParLivroAutor par : todos) {
            if (par.getIdAutor() == idAutor) {
                Livro livro = livroDAO.buscarLivro(par.getIdLivro());
                if (livro != null) {
                    livros.add(livro);
                }
            }
        }
        return livros;
    }

    // DELETE - Remover associacao livro-autor
    public boolean removerAssociacao(int idLivro, int idAutor) throws Exception {
        ParLivroAutor par = new ParLivroAutor(idLivro, idAutor);
        return arvore.delete(par);
    }

    // DELETE - Remover todas as associacoes de um livro
    public boolean removerTodasAssociacoesLivro(int idLivro) throws Exception {
        ArrayList<ParLivroAutor> pares = arvore.read(new ParLivroAutor(idLivro, -1));
        for (ParLivroAutor par : pares) {
            arvore.delete(par);
        }
        return true;
    }

    // DELETE - Remover todas as associacoes de um autor
    public boolean removerTodasAssociacoesAutor(int idAutor) throws Exception {
        ArrayList<ParLivroAutor> todos = arvore.read(null);
        for (ParLivroAutor par : todos) {
            if (par.getIdAutor() == idAutor) {
                arvore.delete(par);
            }
        }
        return true;
    }

    // Verificar se associacao existe
    public boolean associacaoExiste(int idLivro, int idAutor) throws Exception {
        ArrayList<ParLivroAutor> pares = arvore.read(new ParLivroAutor(idLivro, idAutor));
        return !pares.isEmpty();
    }

    // Listar todas as associacoes (para debug)
    public ArrayList<ParLivroAutor> listarTodasAssociacoes() throws Exception {
        return arvore.read(null);
    }
}