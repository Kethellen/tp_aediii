package controller;

import dao.FavoritoDAO;
import model.Favorito;
import model.Usuario;
import dao.UsuarioDAO;
import dao.LivroDAO;
import model.Livro;
import java.util.Scanner;
import controller.FavoritoController;

public class FavoritoController {
    private FavoritoDAO FavoritoDAO;
    Usuario usuarioLogado; 
    Livro livroSelecionado; 
    private Scanner console = new Scanner(System.in);

    public FavoritoController() throws Exception {
        FavoritoDAO = new FavoritoDAO();
    }


    public void menu() {
        int opcao;
        do {
            System.out.println("\n\nAEDsIII");
            System.out.println("-------");
            System.out.println("> Início > Editoras");
            System.out.println("\n1 - Buscar");
            System.out.println("2 - Incluir");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
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
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private void buscarFavorito() {
        System.out.print("\nID do Favorito: ");
        int id = console.nextInt();
        console.nextLine();
        try {
            Favorito favorito = FavoritoDAO.buscarFavorito(id);
            if (favorito != null) {
                System.out.println(favorito);
            } else {
                System.out.println("Favorito não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar fvorito.");
        }
    }

    private void incluirFavorito() {
        System.out.println("\nInclusão de favorito");

        System.out.print("\nNome: ");
        String nome = console.nextLine();
        
        try {
            Favorito Favorito = new Favorito(nome,-1, null);
            if (FavoritoDAO.incluirFavorito(Favorito) > 0) {
                System.out.println("Favorito incluída com sucesso.");
            } else {
                System.out.println("Erro ao incluir favorito.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao incluir favorito.");
        }
    }

    private void alterarFavorito() {
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

    private void excluirFavorito() {
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
}
