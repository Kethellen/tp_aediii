package controller;

import dao.AutorDAO;
import model.Autor;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AutorController {
    private AutorDAO dao = new AutorDAO();

    public void adicionarAutor(int id, String nome, String nacionalidade, LocalDate dataNascimento) {
        try {
            dao.criar(new Autor(id, nome, nacionalidade, dataNascimento));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Autor> listarAutores() {
        try {
            return dao.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Autor buscarAutor(String nome) {
        try {
            return dao.buscarPorNome(nome);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void atualizarAutor(int id, String nome, String nacionalidade, LocalDate dataNascimento) {
        try {
            dao.atualizar(new Autor(id, nome, nacionalidade, dataNascimento));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletarAutor(int id) {
        try {
            dao.deletar(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
