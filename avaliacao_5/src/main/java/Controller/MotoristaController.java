package Controller;

import DAO.MotoristaDAO;
import DAO.VeiculoDAO;
import Model.Motorista;
import Model.Veiculo;

import java.util.List;
import java.util.Scanner;

public class MotoristaController {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int escolha = 0;

        do {
            System.out.print("\n\"-------  MENU Motorista -------\"");
            System.out.println("\n> Selecione uma opção");
            System.out.print(
                    "\n1. Cadastrar novo motorista" +
                            "\n2. Atualizar motorista existente" +
                            "\n3. Listar todos os motoristas" +
                            "\n4. Listar motorista pelo ID" +
                            "\n5. Listar motorista(s) pelo nome" +
                            "\n6. Deletar um motorista\n" +
                            "\n0. Voltar ao menu\n\n> ");
            escolha = input.nextInt();
            input.nextLine();

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

    // 1. Cadastrar novo motorista
    private static void inserir() {
        Motorista Motorista = new Motorista();
        System.out.println("\n++++++ Cadastro de novo Motorista ++++++");
        System.out.print("Digite o nome do motorista: ");
        Motorista.setNome(input.nextLine());
        System.out.print("\nDigite o email do motorista: ");
        Motorista.setEmail(input.nextLine());
        System.out.print("\nDigite o telefone do motorista: ");
        Motorista.setTelefone(input.nextLine());
        System.out.print("\nDigite o código do veículo do motorista: ");
        Integer veiculoId = input.nextInt();

        if (VeiculoDAO.buscarVeiculoPorId(veiculoId) instanceof Veiculo) {
            Motorista.setVeiculoId(veiculoId);
        } else {
            System.out.println("Código de veículo não encontrado.");
            return;
        }

        if (MotoristaDAO.criarMotorista(Motorista)) {
            System.out.println("\nMotorista salvo com sucesso.");
        } else {
            System.out.println("\nOcorreu um erro.");
        }
    }

    // 2. Atualizar motorista já existente
    private static void atualizar() {
        System.out.println("\n++++++ Atualizar dados do motorista ++++++");
        Motorista Motorista = null;
        int escolha = 0;

        do {
            System.out.print("\nEscolha o motorista (código) para atualizar os dados (0 para sair): ");
            int codigo = input.nextInt();
            input.nextLine();

            if (codigo == 0) {
                escolha = 0;
            } else {
                Motorista = MotoristaDAO.buscarMotoristaPorId(codigo);

                if (Motorista == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println("Nome: " + Motorista.getNome());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo nome do motorista: ");
                        Motorista.setNome(input.nextLine());
                    }

                    System.out.println("Email: " + Motorista.getEmail());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.print("Digite o novo email do motorista: ");
                        Motorista.setEmail(input.next());
                    }

                    System.out.println("Telefone: " + Motorista.getTelefone());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.print("Digite o novo telefone do motorista: ");
                        Motorista.setTelefone(input.next());
                    }

                    System.out.println("Veículo: " + Motorista.getVeiculoId());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.print("Digite o novo código de veículo do motorista: ");
                        Integer veiculoId = input.nextInt();

                        if (VeiculoDAO.buscarVeiculoPorId(veiculoId) instanceof Veiculo) {
                            Motorista.setVeiculoId(veiculoId);
                        } else {
                            System.out.println("Código de veículo não encontrado.");
                            return;
                        }
                    }

                    if (MotoristaDAO.atualizarMotorista(Motorista)) {
                        System.out.println("Novos dados salvos: " + Motorista);
                    } else {
                        System.out.println("Ocorreu um erro.");
                    }

                    escolha = 1;
                }

            }
        } while (escolha != 0);
    }

    // 3. Listar todos os motoristas cadastrados
    private static void buscarTodos() {
        System.out.println("\nLista de motoristas cadastrados:\n" + MotoristaDAO.buscarMotoristas());
    }

    // 4. Listar motorista pelo ID
    private static void buscarPorId() {
        System.out.print("\nDigite o código do motorista: ");
        Motorista Motorista = MotoristaDAO.buscarMotoristaPorId(input.nextInt());
        input.nextLine();

        if (Motorista != null) {
            System.out.println(Motorista);
        } else {
            System.out.println("Código não encontrado.");
        }
    }

    // 5. Listar motorista(s) pelo nome
    private static void buscarPorNome() {
        System.out.print("Digite o nome do motorista: ");
        String nome = input.next();
        System.out.println("Pesquisa: " + nome);
        List<Motorista> Motoristas = MotoristaDAO.buscarMotoristasPorNome(nome);

        if (Motoristas.isEmpty()) {
            System.out.println("Não foram encontrados registros correspondentes para: " + nome);
        } else {
            System.out.println(Motoristas);
        }
    }

    // 6. Deletar um motorista
    private static void deletar() {
        System.out.print("Digite o código do motorista para deletar: ");
        Integer id = input.nextInt();
        Boolean result = MotoristaDAO.deletarMotorista(id);

        if (result) {
            System.out.println("Motorista com o código " + id + " deletado com sucesso.");
        } else {
            System.out.println("Código não encontrado.");
        }
    }
}
