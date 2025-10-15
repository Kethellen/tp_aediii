package controller;

import dao.EditoraDAO;
import model.Editora;
import java.util.Scanner;

public class EditoraController {
    private EditoraDAO EditoraDAO;
    private Scanner console = new Scanner(System.in);

    public EditoraController() throws Exception {
        EditoraDAO = new EditoraDAO();
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
                    buscarEditora();
                    break;
                case 2:
                    incluirEditora();
                    break;
                case 3:
                    alterarEditora();
                    break;
                case 4:
                    excluirEditora();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private void buscarEditora() {
        System.out.print("\nID do Editora: ");
        int id = console.nextInt();
        console.nextLine();
        try {
            Editora Editora = EditoraDAO.buscarEditora(id);
            if (Editora != null) {
                System.out.println(Editora);
            } else {
                System.out.println("Editora não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar editora.");
        }
    }

    private void incluirEditora() {
        System.out.println("\nInclusão de editora");

        System.out.print("\nNome: ");
        String nome = console.nextLine();

        try {
            Editora Editora = new Editora(nome);
            if (EditoraDAO.incluirEditora(Editora)) {
                System.out.println("Editora incluída com sucesso.");
            } else {
                System.out.println("Erro ao incluir editora.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao incluir editora.");
        }
    }

    private void alterarEditora() {
        System.out.print("\nID da Editora a ser alterada: ");
        int id = console.nextInt();
        console.nextLine();

        try {
            Editora Editora = EditoraDAO.buscarEditora(id);
            if (Editora == null) {
                System.out.println("Editora não encontrada.");
                return;
            }

            System.out.print("\nNovo nome (vazio para manter): ");
            String nome = console.nextLine();
            if (!nome.isEmpty()) Editora.setNome(nome);

            if (EditoraDAO.alterarEditora(Editora)) {
                System.out.println("Editora alterada com sucesso.");
            } else {
                System.out.println("Erro ao alterar Editora.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar Editora.");
        }
    }

    private void excluirEditora() {
        System.out.print("\nID da Editora a ser excluída: ");
        int id = console.nextInt();
        console.nextLine();

        try {
            Editora Editora = EditoraDAO.buscarEditora(id);
            if (Editora == null) {
                System.out.println("Editora não encontrada.");
                return;
            }

            System.out.print("Confirma exclusão? (S/N): ");
            char resp = console.next().charAt(0);
            if (resp == 'S' || resp == 's') {
                if (EditoraDAO.excluirEditora(id)) {
                    System.out.println("Editora excluída com sucesso.");
                } else {
                    System.out.println("Erro ao excluir Editora.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir Editora.");
        }
    }
}
