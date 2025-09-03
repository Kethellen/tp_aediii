package view;

import controller.AutorController;
import model.Autor;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AutorController controller = new AutorController();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Catálogo de Autores ===");
            System.out.println("1. Adicionar autor");
            System.out.println("2. Listar autores");
            System.out.println("3. Buscar autor por Nome");
            System.out.println("4. Atualizar autor");
            System.out.println("5. Deletar autor");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Nacionalidade: ");
                    String nacionalidade = sc.nextLine();
                    System.out.print("Data de nascimento (AAAA-MM-DD): ");
                    LocalDate dataNascimento = LocalDate.parse(sc.nextLine());
                    controller.adicionarAutor(id, nome, nacionalidade, dataNascimento);
                }
                case 2 -> controller.listarAutores().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Digite o Nome: ");
                    int idBusca = sc.nextInt();
                    System.out.println(controller.buscarAutor(idBusca));
                }
                case 4 -> {
                    System.out.print("ID do autor: ");
                    int idAt = sc.nextInt(); sc.nextLine();
                    System.out.print("Novo nome: ");
                    String nomeAt = sc.nextLine();
                    System.out.print("Nova nacionalidade: ");
                    String nacionalidadeAt = sc.nextLine();
                    System.out.print("Nova data de nascimento (AAAA-MM-DD): ");
                    LocalDate dataNascAt = LocalDate.parse(sc.nextLine());
                    controller.atualizarAutor(idAt, nomeAt, nacionalidadeAt, dataNascAt);
                }
                case 5 -> {
                    System.out.print("ID do autor a deletar: ");
                    int idDel = sc.nextInt();
                    controller.deletarAutor(idDel);
                }
                case 0 -> {
                    System.out.println("Saindo...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
