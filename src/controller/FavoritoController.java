package controller;

import dao.FavoritoDAO;
import model.Favorito;
import model.Usuario;
import dao.LivroDAO;
import model.Livro;

import java.util.ArrayList;
import java.util.Scanner;
import controller.FavoritoController;

public class FavoritoController {
    private FavoritoDAO FavoritoDAO;
    Usuario usuarioLogado = UsuarioController.getUsuarioLogado(); 
    Livro livroSelecionado; 
    private Scanner console = new Scanner(System.in);

    public FavoritoController() throws Exception {
        FavoritoDAO = new FavoritoDAO();
    }


    public void menu() throws Exception{
        int opcao;
        do {
            System.out.println("\n\nAEDsIII");
            System.out.println("-------");
            System.out.println("> Início > Favoritos");
            System.out.println("\n1 - Mostrar meus favotitos");
            System.out.println("2 - Incluir");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Catalogo de Livros");
            System.out.println("0 - Voltar");

            System.out.print("\nOpção: ");
            try {
                opcao = Integer.valueOf(console.nextLine());
            } catch(NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    buscarFavorito();
                    break;
                case 2:
                    incluirFavorito();
                    break;
                case 3:
                    alterarFavorito();
                    break;
                case 4:
                    excluirFavorito();
                    break;
                case 5:
                    catalogoDeLivros();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    public void menuUsuario() throws Exception{
        int opcao;
        do {
            System.out.println("\n\nAEDsIII");
            System.out.println("-------");
            System.out.println("> Início > Favoritos");
            System.out.println("\n1 - Mostrar meus favotitos");
            System.out.println("2 - Incluir");
            System.out.println("3 - Excluir");
            System.out.println("4 - Catalogo de Livros");
            System.out.println("0 - Voltar");

            System.out.print("\nOpção: ");
            try {
                opcao = Integer.valueOf(console.nextLine());
            } catch(NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    buscarFavorito();
                    break;
                case 2:
                    incluirFavorito();
                    break;
                case 3:
                    excluirFavorito();
                    break;
                case 4:
                    catalogoDeLivros();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    public void buscarFavorito() {
        System.out.print("\nID do Favorito: ");
        
        if (!isLogin()) {
            try {
                ArrayList<Favorito> favoritos = FavoritoDAO.listarPorUsuario(usuarioLogado.getId());
                if (favoritos.isEmpty()) {
                    System.out.println("Favorito não encontrado.");
                } else {
                    System.out.println("\nMeus Favoritos:");
                    for (Favorito f : favoritos) {
                        System.out.println("[" + f.getId() + "] " + f.getNome() + " (ISBN: " + f.getIsbn() + ")");
                    }
                }
            } catch (Exception e) {
            System.out.println("Erro ao buscar fvorito.");
        }
        }else{
            System.out.println("Faça login para incluir Favorito.");
            return;
        }
    }

    public boolean isLogin(){
        return (usuarioLogado == null) ? true: false;
    }

    public void incluirFavorito() throws Exception{
        System.out.println("\nInclusão de favorito");

        if (!isLogin()) {
            System.out.print("\nDigite o ID do livro que deseja favoritar: ");
            int idLivro = Integer.parseInt(console.nextLine());

            LivroDAO livroDAO = new LivroDAO();

            try {
                Livro livro = livroDAO.buscarLivro(idLivro);
                Favorito Favorito = new Favorito(livro.getTitulo(),usuarioLogado.getId(), livro.getIsbn());
                if (FavoritoDAO.incluirFavorito(Favorito) > 0) {
                    System.out.println("Favorito incluída com sucesso.");
                } else {
                    System.out.println("Erro ao incluir favorito.");
                }
            } catch (Exception e) {
                System.out.println("Erro ao incluir favorito.");
            }
        }else{
            System.out.println("Faça login para incluir Favorito.");
            return;
        }
        
    }

    public void alterarFavorito() {
        System.out.print("\nID da Favorito a ser alterada: ");
        int id = console.nextInt();
        console.nextLine();

        try {
            Favorito Favorito = FavoritoDAO.buscarFavorito(id);
            if (Favorito == null) {
                System.out.println("Favorito não encontrada.");
                return;
            }

            System.out.print("\nNovo nome (vazio para manter): ");
            String nome = console.nextLine();
            if (!nome.isEmpty()) Favorito.setNome(nome);

            if (FavoritoDAO.alterarFavorito(Favorito)) {
                System.out.println("Favorito alterada com sucesso.");
            } else {
                System.out.println("Erro ao alterar Favorito.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar Favorito.");
        }
    }

    public void excluirFavorito() {
        System.out.print("\nID da Favorito a ser excluída: ");
        int id = console.nextInt();
        console.nextLine();

        try {
            Favorito favorito = FavoritoDAO.buscarFavorito(id);
            if (favorito == null) {
                System.out.println("Favorito não encontrada.");
                return;
            }

            System.out.print("Confirma exclusão? (S/N): ");
            char resp = console.next().charAt(0);
            if (resp == 'S' || resp == 's') {
                if (FavoritoDAO.excluirFavorito(id)) {
                    System.out.println("Favorito excluída com sucesso.");
                } else {
                    System.out.println("Erro ao excluir Favorito.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir Favorito.");
        }
    }

    public ArrayList<Livro> catalogoDeLivros() throws Exception {
        LivroDAO livroDAO = new LivroDAO();
        ArrayList<Livro> livros = livroDAO.listarTodos();

        System.out.println("\nCatálogo de livros:");
        for (Livro l : livros) {
            System.out.println("[" + l.getId() + "] " + l.getTitulo() +" - "+ l.getGenero() + " (" + l.getIsbn() + ")");
        }

        return livros;
    }
}
