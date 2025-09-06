package controller;

import dao.AutorDAO;
import model.Autor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AutorController {
    private AutorDAO autorDAO;
    private Scanner console = new Scanner(System.in);

    public AutorController() throws Exception {
        autorDAO = new AutorDAO();
    }


    public void menu() {
        int opcao;
        do {
            System.out.println("\n\nAEDsIII");
            System.out.println("-------");
            System.out.println("> Início > Autores");
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
                    buscarAutor();
                    break;
                case 2:
                    incluirAutor();
                    break;
                case 3:
                    alterarAutor();
                    break;
                case 4:
                    excluirAutor();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private void buscarAutor() {
        System.out.print("\nID do autor: ");
        int id = console.nextInt();
        console.nextLine();
        try {
            Autor autor = autorDAO.buscarAutor(id);
            if (autor != null) {
                System.out.println(autor);
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar cliente.");
        }
    }

    private void incluirAutor() {
        System.out.println("\nInclusão de cliente");

        System.out.print("\nNome: ");
        String nome = console.nextLine();
        System.out.print("Nacionalidade: ");
        String nacionalidade = console.nextLine();
        System.out.print("Data de nascimento (DD/MM/AAAA): ");
        String dataStr = console.nextLine();
        LocalDate nascimento = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        try {
            Autor autor = new Autor(nome, nacionalidade, nascimento);
            if (autorDAO.incluirAutor(autor)) {
                System.out.println("Cliente incluído com sucesso.");
            } else {
                System.out.println("Erro ao incluir cliente.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao incluir cliente.");
        }
    }

    private void alterarAutor() {
        System.out.print("\nID do Autor a ser alterado: ");
        int id = console.nextInt();
        console.nextLine();

        try {
            Autor autor = autorDAO.buscarAutor(id);
            if (autor == null) {
                System.out.println("Autor não encontrado.");
                return;
            }

            System.out.print("\nNovo nome (vazio para manter): ");
            String nome = console.nextLine();
            if (!nome.isEmpty()) autor.setNome(nome);

            System.out.print("Novo nacionalidade: ");
            String nacionalidade = console.nextLine();
            if (!nacionalidade.isEmpty()) autor.setNacionalidade(nacionalidade);

            System.out.print("Nova data de nascimento (DD/MM/AAAA, vazio para manter): ");
            String dataStr = console.nextLine();
            if (!dataStr.isEmpty()) autor.setDataNascimento(LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            if (autorDAO.alterarAutor(autor)) {
                System.out.println("Autor alterado com sucesso.");
            } else {
                System.out.println("Erro ao alterar autor.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar autor.");
        }
    }

    private void excluirAutor() {
        System.out.print("\nID do autor a ser excluído: ");
        int id = console.nextInt();
        console.nextLine();

        try {
            Autor autor = autorDAO.buscarAutor(id);
            if (autor == null) {
                System.out.println("Autor não encontrado.");
                return;
            }

            System.out.print("Confirma exclusão? (S/N): ");
            char resp = console.next().charAt(0);
            if (resp == 'S' || resp == 's') {
                if (autorDAO.excluirAutor(id)) {
                    System.out.println("Autor excluído com sucesso.");
                } else {
                    System.out.println("Erro ao excluir autor.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir autor.");
        }
    }
}
