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

    // 1. Cadastrar novo motorista
    private static void inserir() {
        Motorista motorista = new Motorista();
        System.out.println("\n++++++ Cadastro de novo Motorista ++++++");
        System.out.print("Digite o nome do motorista: ");
        motorista.setNome(input.nextLine());
        System.out.print("\nDigite o email do motorista: ");
        motorista.setEmail(input.nextLine());
        System.out.print("\nDigite o telefone do motorista: ");
        motorista.setTelefone(input.nextLine());
        System.out.print("\nDigite o código do veículo do motorista: ");

        if (input.hasNextInt()) {
            Veiculo veiculo = VeiculoDAO.buscarVeiculoPorId(input.nextInt());

            if (veiculo instanceof Veiculo) {
                motorista.setVeiculo(veiculo);
            } else {
                System.out.println("Código de veículo não encontrado.");
            }

            if (MotoristaDAO.criarMotorista(motorista)) {
                System.out.println("\nMotorista salvo com sucesso.");
            } else {
                System.out.println("\nOcorreu um erro.");
            }
        } else {
            System.out.println("Codigo invalido.");
            input.nextLine();
        }
    }

    // 2. Atualizar motorista já existente
    private static void atualizar() {
        System.out.println("\n++++++ Atualizar dados do motorista ++++++");
        Motorista motorista = null;
        int escolha = 0;

        do {
            System.out.print("\nEscolha o motorista (código) para atualizar os dados (0 para sair): ");

            int codigo;
            if (input.hasNextInt()) {
                codigo = input.nextInt();
            } else {
                codigo = 0;
            }
            input.nextLine();

            if (codigo == 0) {
                escolha = 0;
            } else {
                motorista = MotoristaDAO.buscarMotoristaPorId(codigo);

                if (motorista == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println("Nome: " + motorista.getNome());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.hasNextInt()) {
                        if (input.nextInt() == 0) {
                            input.nextLine();
                            System.out.println("Digite o novo nome do motorista: ");
                            motorista.setNome(input.nextLine());
                        }
                    } else {
                        System.out.println("Codigo invalido");
                        return;
                    }

                    System.out.println("Email: " + motorista.getEmail());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.hasNextInt()) {
                        if (input.nextInt() == 0) {
                            input.nextLine();
                            System.out.print("Digite o novo email do motorista: ");
                            motorista.setEmail(input.next());
                        }
                    } else {
                        System.out.println("Codigo invalido");
                        return;
                    }

                    System.out.println("Telefone: " + motorista.getTelefone());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.hasNextInt()) {
                        if (input.nextInt() == 0) {
                            input.nextLine();
                            System.out.print("Digite o novo telefone do motorista: ");
                            motorista.setTelefone(input.next());
                        }
                    } else {
                        System.out.println("Codigo invalido");
                        return;
                    }

                    System.out.println("Veículo: " + motorista.getVeiculo().getId());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.hasNextInt()) {
                        if (input.nextInt() == 0) {
                            input.nextLine();
                            System.out.print("Digite o novo código de veículo do motorista: ");


                            if (input.hasNextInt()) {
                                Integer veiculoId = input.nextInt();
                                if (VeiculoDAO.buscarVeiculoPorId(veiculoId) instanceof Veiculo) {
                                    motorista.getVeiculo().setId(veiculoId);
                                } else {
                                    System.out.println("Código de veículo não encontrado.");
                                    return;
                                }
                            } else {
                                System.out.println("Codigo invalido.");
                                return;
                            }

                        }
                    } else {
                        System.out.println("Codigo invalido.");
                        return;
                    }

                    if (MotoristaDAO.atualizarMotorista(motorista)) {
                        System.out.println("Novos dados salvos: " + motorista);
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
