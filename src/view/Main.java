package view;

import controller.AutorController;
import controller.EditoraController;
import controller.LivroController;
import controller.UsuarioController;
import controller.FavoritoController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int opcao;

        try {
            do {
                System.out.println("\n\nAEDsIII");
                System.out.println("-------");
                System.out.println("> Início");
                System.out.println("\n1 - Autor");
                System.out.println("2 - Editora");
                System.out.println("3 - Livro");
                System.out.println("4 - Usuario");
                System.out.println("5 - Favorito");
                System.out.println("0 - Sair");

                System.out.print("\nOpção: ");
                try {
                    opcao = Integer.valueOf(console.nextLine());
                } catch (NumberFormatException e) {
                    opcao = -1;
                }

                switch (opcao) {
                    case 1:
                        AutorController autorController = new AutorController();
                        autorController.menu();
                        break;
                    case 2:
                        EditoraController editoraController = new EditoraController();
                        editoraController.menu();
                        break;
                    case 3:
                        LivroController livroController = new LivroController();
                        livroController.menu();
                        break;
                    case 4:
                        UsuarioController usuarioController = new UsuarioController();
                        usuarioController.menu();  
                    case 5:
                        FavoritoController favoritoControle = new FavoritoController();
                        favoritoControle.menu();      
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } while (opcao != 0);

        } catch (Exception e) {
            System.err.println("Erro fatal no sistema:");
            e.printStackTrace();
        } finally {
            console.close();
        }
    }
}
