package controller;

import dao.LivroDAO;
import model.Livro;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LivroController {
    private LivroDAO LivroDAO;
    private Scanner console = new Scanner(System.in);

    public LivroController() throws Exception {
        LivroDAO = new LivroDAO();
    }


    public void menu() {
        int opcao;
        do {
            System.out.println("\n\nAEDsIII");
            System.out.println("-------");
            System.out.println("> Início > Livros");
            System.out.println("\n1 - Buscar");
            System.out.println("2 - Incluir");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Buscar por ID_Editora");
            System.out.println("0 - Voltar");

            System.out.print("\nOpção: ");
            try {
                opcao = Integer.valueOf(console.nextLine());
            } catch(NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    incluirLivro();
                    break;
                case 3:
                    alterarLivro();
                    break;
                case 4:
                    excluirLivro();
                    break;
                case 5:
                    buscaLivrosPorEditora();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private void buscarLivro() {
        System.out.print("\nID do Livro: ");
        int id = console.nextInt();
        console.nextLine();
        try {
            Livro Livro = LivroDAO.buscarLivro(id);
            if (Livro != null) {
                System.out.println(Livro);
            } else {
                System.out.println("Livro não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro.");
        }
    }

    private void incluirLivro() {
        System.out.println("\nInclusão de livro");
        System.out.print("\nISBN:");
        String isbn = console.nextLine();
        System.out.print("Titulo: ");
        String titulo = console.nextLine();
        System.out.print("Genero: ");
        String genero = console.nextLine();
        System.out.print("Edicao: ");
        String edicao = console.nextLine();
        System.out.print("Prefixo: ");
        String prefixo = console.nextLine();
        System.out.print("Pais de Origem: ");
        String paisOrigem = console.nextLine();
        System.out.print("Data de publicacao (DD/MM/AAAA): ");
        String dataStr = console.nextLine();
        LocalDate dataPublicacao = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Id Editora: ");
        int editoraId = console.nextInt();

        try {
            Livro Livro = new Livro(isbn, titulo, genero, edicao, prefixo, paisOrigem, dataPublicacao, editoraId);
            if (LivroDAO.incluirLivro(Livro)) {
                System.out.println("Livro incluído com sucesso.");
            } else {
                System.out.println("Erro ao incluir livro.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao incluir livro.");
        }
    }

    private void alterarLivro() {
        System.out.print("\nID do Livro a ser alterado: ");
        int id = console.nextInt();
        console.nextLine();

        try {
            Livro Livro = LivroDAO.buscarLivro(id);
            if (Livro == null) {
                System.out.println("Livro não encontrado.");
                return;
            }

            System.out.print("\nNovo ISBN (vazio para manter): ");
            String isbn = console.nextLine();
            if (!isbn.isEmpty()) Livro.setIsbn(isbn);

            System.out.print("Novo título (vazio para manter): ");
            String titulo = console.nextLine();
            if (!titulo.isEmpty()) Livro.setTitulo(titulo);

            System.out.print("Novo genero: ");
            String genero = console.nextLine();
            if (!genero.isEmpty()) Livro.setGenero(genero);

            System.out.print("Nova edição: ");
            String edicao = console.nextLine();
            if (!edicao.isEmpty()) Livro.setEdicao(edicao);

            System.out.print("Novo prefixo: ");
            String prefixo = console.nextLine();
            if (!prefixo.isEmpty()) Livro.setPrefixo(prefixo);

            System.out.print("Novo país origem: ");
            String paisOrigem = console.nextLine();
            if (!paisOrigem.isEmpty()) Livro.setPaisOrigem(paisOrigem);

            System.out.print("Nova data de publicação (DD/MM/AAAA, vazio para manter): ");
            String dataStr = console.nextLine();
            if (!dataStr.isEmpty()) Livro.setDataPublicacao(LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            if (LivroDAO.alterarLivro(Livro)) {
                System.out.println("Livro alterado com sucesso.");
            } else {
                System.out.println("Erro ao alterar Livro.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar Livro.");
        }
    }

    private void excluirLivro() {
        System.out.print("\nID do Livro a ser excluído: ");
        int id = console.nextInt();
        console.nextLine();

        try {
            Livro Livro = LivroDAO.buscarLivro(id);
            if (Livro == null) {
                System.out.println("Livro não encontrado.");
                return;
            }

            System.out.print("Confirma exclusão? (S/N): ");
            char resp = console.next().charAt(0);
            if (resp == 'S' || resp == 's') {
                if (LivroDAO.excluirLivro(id)) {
                    System.out.println("Livro excluído com sucesso.");
                } else {
                    System.out.println("Erro ao excluir Livro.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir Livro.");
        }
    }

    private void buscaLivrosPorEditora() {
        System.out.print("\nID da Editora: ");
        int id = console.nextInt();
        console.nextLine();
        try {
            Livro[] livros = LivroDAO.buscaLivrosPorEditora(id);
            if (livros.length != 0) {
                for(int i = 0; i<livros.length; i++){
                    System.out.println(livros[i]);
                }
            } else {
                System.out.println("Nenhum livro encontrado para a editora.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar livros.");
        }
    }
}
