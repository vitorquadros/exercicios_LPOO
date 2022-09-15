package Controller;

import DAO.UsuarioDAO;
import Model.Usuario;
import com.sun.tools.javac.Main;

import java.util.List;
import java.util.Scanner;

public class UsuarioController {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int escolha = 0;

        do {
            System.out.print("\n\"-------  MENU Usuário -------\"");
            System.out.println("\n> Selecione uma opção");
            System.out.print(
                    "\n1. Cadastrar novo usuário" +
                            "\n2. Atualizar usuário existente" +
                            "\n3. Listar todos os usuários" +
                            "\n4. Listar usuário pelo ID" +
                            "\n5. Listar usuário(s) pelo nome" +
                            "\n6. Deletar um usuário\n" +
                            "\n0. Voltar ao menu\n\n> ");
            if (input.hasNextInt()) {
                escolha = input.nextInt();
                input.nextLine();
            } else {
                escolha = -1;
                input.nextLine();
            }


            switch (escolha) {
                case 1:
                    inserir();
                    break;
                case 2:
                    atualizar();
                    break;
                case 3:
                    buscarTodos();
                    break;
                case 4:
                    buscarPorId();
                    break;
                case 5:
                    buscarPorNome();
                    break;
                case 6:
                    deletar();
                    break;
                default:
                    if (escolha != 0)
                        System.out.println("Opção inválida.");
            }
        } while (escolha != 0);
    }

    // 1. Cadastrar novo usuário
    private static void inserir() {
        Usuario usuario = new Usuario();
        System.out.println("\n++++++ Cadastro de novo Usuário ++++++");
        System.out.print("Digite o nome do usuário: ");
        usuario.setNome(input.nextLine());
        System.out.print("\nDigite o email do usuário: ");
        usuario.setEmail(input.nextLine());
        System.out.print("\nDigite o telefone do usuário: ");
        usuario.setTelefone(input.nextLine());

        if (UsuarioDAO.criarUsuario(usuario)) {
            System.out.println("\nUsuário salvo com sucesso.");
        } else {
            System.out.println("\nOcorreu um erro.");
        }
    }

    // 2. Atualizar usuário já existente
    private static void atualizar() {
        System.out.println("\n++++++ Atualizar dados do usuário ++++++");
        Usuario usuario = null;
        int escolha = 0;

        do {
            System.out.print("\nEscolha o usuário (código) para atualizar os dados (0 para sair): ");
            int codigo = input.nextInt();
            input.nextLine();

            if (codigo == 0) {
                escolha = 0;
            } else {
                usuario = UsuarioDAO.buscarUsuarioPorId(codigo);

                if (usuario == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println("Nome: " + usuario.getNome());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo nome do usuário: ");
                        usuario.setNome(input.nextLine());
                    }

                    System.out.println("Email: " + usuario.getEmail());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.print("Digite o novo email do usuário: ");
                        usuario.setEmail(input.next());
                    }

                    System.out.println("Telefone: " + usuario.getTelefone());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.print("Digite o novo telefone do usuário: ");
                        usuario.setTelefone(input.next());
                    }

                    if (UsuarioDAO.atualizarUsuario(usuario)) {
                        System.out.println("Novos dados salvos: " + usuario);
                    } else {
                        System.out.println("Ocorreu um erro.");
                    }

                    escolha = 1;
                }

            }
        } while (escolha != 0);
    }

    // 3. Listar todos os usuários cadastrados
    private static void buscarTodos() {
        System.out.println("\nLista de usuários cadastrados:\n" + UsuarioDAO.buscarUsuarios());
    }

    // 4. Listar usuário pelo ID
    private static void buscarPorId() {
        System.out.print("\nDigite o código do usuário: ");
        Usuario usuario = UsuarioDAO.buscarUsuarioPorId(input.nextInt());
        input.nextLine();

        if (usuario != null) {
            System.out.println(usuario);
        } else {
            System.out.println("Código não encontrado.");
        }
    }

    // 5. Listar usuário(s) pelo nome
    private static void buscarPorNome() {
        System.out.print("Digite o nome do usuário: ");
        String nome = input.next();
        System.out.println("Pesquisa: " + nome);
        List<Usuario> usuarios = UsuarioDAO.buscarUsuariosPorNome(nome);

        if (usuarios.isEmpty()) {
            System.out.println("Não foram encontrados registros correspondentes para: " + nome);
        } else {
            System.out.println(usuarios);
        }
    }

    // 6. Deletar um usuário
    private static void deletar() {
        System.out.print("Digite o código do usuário para deletar: ");
        Integer id = input.nextInt();
        Boolean result = UsuarioDAO.deletarUsuario(id);

        if (result) {
            System.out.println("Usuário com o código " + id + " deletado com sucesso.");
        } else {
            System.out.println("Código não encontrado.");
        }
    }
}
