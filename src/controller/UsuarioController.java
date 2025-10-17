package controller;

import dao.UsuarioDAO;
import model.Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UsuarioController {
    private UsuarioDAO userDAO;
    private Scanner console = new Scanner(System.in);

    public UsuarioController() throws Exception {
        userDAO = new UsuarioDAO();
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
                    buscarUsuario();
                    break;
                case 2:
                    incluirUsuario();
                    break;
                case 3:
                    alterarUsuario();
                    break;
                case 4:
                    excluirUsuario();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private void buscarUsuario() {
        System.out.print("\nID do usuario: ");
        int id = console.nextInt();
        console.nextLine();
        try {
            Usuario user = userDAO.buscarUser(id);
            if (user != null) {
                System.out.println(user.getNome());
            } else {
                System.out.println("Usuario não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar Usuario.");
            e.printStackTrace();
        }
    }

    private void incluirUsuario() {
        System.out.println("\nInclusão de usuario");

        System.out.print("\nNome: ");
        String nome = console.nextLine();
        System.out.print("\nNick: ");
        String nick = console.nextLine();
        System.out.print("\nNacionalidade: ");
        String nacionalidade = console.nextLine();
        System.out.print("\nData de nascimento (DD/MM/AAAA): ");
        String dataStr = console.nextLine();
        LocalDate nascimento = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("\nIdade: ");
        int idade = console.nextInt();
        System.out.print("\nTelefone: ");
        String telefone = console.nextLine();
        console.nextLine();
        System.out.print("\nEmail: ");
        String email = console.nextLine();
        System.out.print("\nCaminho da Foto: ");
        String foto = console.nextLine();
        
        try {
            Usuario usuario = new Usuario(nome, nick, nacionalidade, nascimento, idade, telefone, email, foto);
            int id = userDAO.incluirUser(usuario);
            if (id > 0) {
                System.out.println("Usuario id "+ id + " incluído com sucesso.");
            } else {
                System.out.println("Erro ao incluir usuario.");
            }
        } catch (Exception e) {
            System.out.println("Erro na inserção usuario." + e.getMessage());
            e.printStackTrace();
        }
    }

    private void alterarUsuario() {
        System.out.print("\nID do usuario a ser alterado: ");
        int id = console.nextInt();
        console.nextLine();

        try {
            Usuario usuario = userDAO.buscarUser(id);
            if (usuario == null) {
                System.out.println("Usuario não encontrado.");
                return;
            }

            System.out.print("\nNovo usuario (vazio para manter): ");
            String nome = console.nextLine();
            if (!nome.isEmpty()) usuario.setNome(nome);

            System.out.print("Novo nacionalidade: ");
            String nacionalidade = console.nextLine();
            if (!nacionalidade.isEmpty()) usuario.setNacionalidade(nacionalidade);

            System.out.print("Nova data de nascimento (DD/MM/AAAA, vazio para manter): ");
            String dataStr = console.nextLine();
            if (!dataStr.isEmpty()) usuario.setDataNascimento(LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            if (userDAO.alterarUser(usuario)) {
                System.out.println("Usuario alterado com sucesso.");
            } else {
                System.out.println("Erro ao alterar usuario.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar usuario.");
        }
    }

    private void excluirUsuario() {
        System.out.print("\nID do usuario a ser excluído: ");
        int id = console.nextInt();
        console.nextLine();

        try {
            Usuario usuario = userDAO.buscarUser(id);
            if (usuario == null) {
                System.out.println("Usuario não encontrado.");
                return;
            }

            System.out.print("Confirma exclusão? (S/N): ");
            char resp = console.next().charAt(0);
            if (resp == 'S' || resp == 's') {
                if (userDAO.excluirUser(id)) {
                    System.out.println("Usuario excluído com sucesso.");
                } else {
                    System.out.println("Erro ao excluir usuario.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir usuario.");
        }
    }
}
