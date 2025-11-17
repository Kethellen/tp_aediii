package controller;

import dao.UsuarioDAO;
import model.Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UsuarioController {
    private UsuarioDAO userDAO;
    private static Usuario usuarioLogado;
    private Scanner console = new Scanner(System.in);

    public UsuarioController() throws Exception {
        userDAO = new UsuarioDAO();
    }


    public void menu() throws Exception{
        int opcao;
        do {
            System.out.println("\n\nAEDsIII");
            System.out.println("-------");
            System.out.println("> Início > Usuário");
            System.out.println("\n1 - Buscar");
            System.out.println("2 - Incluir");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Login");
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
                case 5:
                    login();
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
                user.toString();
                // Verifica se usuario tem imagem cadastrada
                if (user.getFotoOffset() > -1) {

                    byte[] bytes = userDAO.buscarFoto(user.getFotoOffset());

                    // Saber qual a extensão png ou jpeg
                    String ext = ".bin";
                    if ("image/png".equals(user.getTipo())) {ext = ".png";}
                    if ("image/jpeg".equals(user.getTipo())) {ext = ".jpg";}  

                    // Armazenar imagem
                    java.nio.file.Path tmp = java.nio.file.Paths.get("./imagem/imagemUsuario/fotoUsuario_" + user.getId() + ext);
                    
                    // Escrever os bytes no arquivo
                    java.nio.file.Files.write(tmp, bytes);

                    System.out.println("Imagem salva em: " + tmp.toAbsolutePath());
                    
                }else{
                    System.out.println("Nenhuma imagem cadastrada para este usuario.");
                }
            } else {
                System.out.println("Usuario não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar Usuario.");
            e.printStackTrace();
        }
    }

    private void incluirUsuario() {
        System.out.println("\nCadastro de usuário");

        // === Entrada de dados ===
        System.out.print("Nome: ");
        String nome = console.nextLine();

        System.out.print("Nick: ");
        String nick = console.nextLine();

        System.out.print("Nacionalidade: ");
        String nacionalidade = console.nextLine();

        System.out.print("Data de nascimento (DD/MM/AAAA): ");
        LocalDate nascimento = LocalDate.parse(console.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Telefone: ");
        String telefone = console.nextLine();

        System.out.print("Email: ");
        String email = console.nextLine();

        System.out.print("Crie uma senha de 6 dígitos: ");
        String senha = console.nextLine();

        try {
            // === Caminho da imagem ===
            java.nio.file.Path caminhoImagem = null;
            while (true) {
                System.out.print("Caminho da foto: ");
                String caminho = console.nextLine();
                caminhoImagem = java.nio.file.Paths.get(caminho);

                if (java.nio.file.Files.exists(caminhoImagem))
                    break;
                else
                    System.out.println("Caminho inválido. Tente novamente.");
            }

            // === Leitura e gravação da imagem ===
            byte[] bytes = java.nio.file.Files.readAllBytes(caminhoImagem);
            long offset = userDAO.incluirFoto(bytes);

            String nomeArquivo = caminhoImagem.getFileName().toString().toLowerCase();
            String tipo = detectaMime(nomeArquivo);

            // === Criação e gravação do usuário ===
            Usuario usuario = new Usuario(nome, nick, nacionalidade, nascimento,
                                        telefone, email, offset, bytes.length, tipo, senha);

            int id = userDAO.incluirUser(usuario);

            System.out.println(id > 0
                    ? "Usuário ID " + id + " incluído com sucesso."
                    : "Erro ao incluir usuário.");

        } catch (Exception e) {
            System.out.println("Erro ao incluir usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String detectaMime(String nome) {
        nome = nome.toLowerCase();
        if (nome.endsWith(".png")) return "image/png";
        if (nome.endsWith(".jpg") || nome.endsWith(".jpeg")) return "image/jpeg";
        return "application/octet-stream";
    }

    private void alterarUsuario() {
        System.out.print("\nID do usuário a ser alterado: ");
        int id = console.nextInt();
        console.nextLine();

        try {
            Usuario usuario = userDAO.buscarUser(id);
            if (usuario == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            System.out.println("\nDeixe o campo vazio para manter o valor atual.");

            System.out.print("Novo nome: ");
            String nome = console.nextLine();
            if (!nome.isEmpty()) usuario.setNome(nome);

            System.out.print("Novo nick: ");
            String nick = console.nextLine();
            if (!nick.isEmpty()) usuario.setNick(nick);

            System.out.print("Nova nacionalidade: ");
            String nacionalidade = console.nextLine();
            if (!nacionalidade.isEmpty()) usuario.setNacionalidade(nacionalidade);

            System.out.print("Nova data de nascimento (DD/MM/AAAA): ");
            String dataStr = console.nextLine();
            if (!dataStr.isEmpty())
                usuario.setDataNascimento(LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            System.out.print("Novo telefone: ");
            String telefone = console.nextLine();
            if (!telefone.isEmpty()) usuario.setTelefone(telefone);

            System.out.print("Novo e-mail: ");
            String email = console.nextLine();
            if (!email.isEmpty()) usuario.setEmail(email);

            System.out.print("Nova senha (6 dígitos): ");
            String senha = console.nextLine();
            if (!senha.isEmpty()) usuario.setSenha(senha);

            System.out.print("Novo caminho da foto (vazio para manter): ");
            String caminho = console.nextLine();

            if (!caminho.isEmpty()) {
                java.nio.file.Path caminhoImagem = java.nio.file.Paths.get(caminho);
                if (java.nio.file.Files.exists(caminhoImagem)) {
                    byte[] novaFoto = java.nio.file.Files.readAllBytes(caminhoImagem);
                    String tipo = detectaMime(caminhoImagem.getFileName().toString());

                    // Atualiza imagem no blob
                    long novoOffset;
                    if (usuario.getFotoOffset() > -1)
                        novoOffset = userDAO.alterarFoto(usuario.getFotoOffset(), novaFoto);
                    else
                        novoOffset = userDAO.incluirFoto(novaFoto);

                    usuario.setFotoOffset(novoOffset);
                    usuario.setFotoLength(novaFoto.length);
                    usuario.setTipo(tipo);
                    
                } else {
                    System.out.println("Caminho inválido. A imagem antiga será mantida.");
                }
            }

            if (userDAO.alterarUser(usuario))
                System.out.println("Usuário alterado com sucesso!");
            else
                System.out.println("Erro ao alterar usuário.");

        } catch (Exception e) {
            System.out.println("Erro ao alterar usuário: " + e.getMessage());
            e.printStackTrace();
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
                if (userDAO.excluirUser(id) && userDAO.excluirFoto(usuario.getFotoOffset())) {
                    System.out.println("Usuario excluído com sucesso.");
                } else {
                    System.out.println("Erro ao excluir usuario.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir usuario.");
        }
    }
    
    public void login() throws Exception {
        System.out.print("Email: ");
        String email = console.nextLine();
        System.out.print("Senha: ");
        String senha = console.nextLine();

        usuarioLogado = userDAO.login(email, senha);
        if (usuarioLogado == null) {
            System.out.println("Usuário ou senha incorretos!");
        } else {
            System.out.println("Login realizado com sucesso!");
            System.out.println("Bem-vindo, " + usuarioLogado.getNome() + "!"); 
        }
    }
    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}
