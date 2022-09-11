package Controller;

import DAO.VeiculoDAO;
import Model.Veiculo;

import java.util.List;
import java.util.Scanner;

public class VeiculoController {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int escolha = 0;

        do {
            System.out.print("\n\"-------  MENU Veiculo -------\"");
            System.out.println("\n> Selecione uma opção");
            System.out.print(
                    "\n1. Cadastrar novo veiculo" +
                            "\n2. Atualizar veiculo existente" +
                            "\n3. Listar todos os veiculos" +
                            "\n4. Listar veiculo pelo ID" +
                            "\n5. Listar veiculo(s) pelo ano de fabricação" +
                            "\n6. Deletar um veiculo\n" +
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
                    buscarPorAno();
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

    // 1. Cadastrar novo veiculo
    private static void inserir() {
        Veiculo Veiculo = new Veiculo();
        System.out.println("\n++++++ Cadastro de novo Veiculo ++++++");
        System.out.print("Digite o tipo do veiculo: ");
        Veiculo.setTipo(input.nextLine());
        System.out.print("\nDigite a placa do veiculo: ");
        Veiculo.setPlaca(input.nextLine());
        System.out.print("\nDigite o ano de fabricação do veiculo: ");
        Veiculo.setAnoFabricacao(input.nextLine());

        if (VeiculoDAO.criarVeiculo(Veiculo)) {
            System.out.println("\nVeiculo salvo com sucesso.");
        } else {
            System.out.println("\nOcorreu um erro.");
        }
    }

    // 2. Atualizar veiculo já existente
    private static void atualizar() {
        System.out.println("\n++++++ Atualizar dados do veiculo ++++++");
        Veiculo Veiculo = null;
        int escolha = 0;

        do {
            System.out.print("\nEscolha o veiculo (código) para atualizar os dados (0 para sair): ");
            int codigo = input.nextInt();
            input.nextLine();

            if (codigo == 0) {
                escolha = 0;
            } else {
                Veiculo = VeiculoDAO.buscarVeiculoPorId(codigo);

                if (Veiculo == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println("Tipo: " + Veiculo.getTipo());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo tipo do veiculo: ");
                        Veiculo.setTipo(input.nextLine());
                    }

                    System.out.println("Placa: " + Veiculo.getPlaca());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.print("Digite a nova placa do veiculo: ");
                        Veiculo.setPlaca(input.next());
                    }

                    System.out.println("Ano de fabricação: " + Veiculo.getAnoFabricacao());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.print("Digite o novo ano de fabricação do veiculo: ");
                        Veiculo.setAnoFabricacao(input.next());
                    }

                    if (VeiculoDAO.atualizarVeiculo(Veiculo)) {
                        System.out.println("Novos dados salvos: " + Veiculo);
                    } else {
                        System.out.println("Ocorreu um erro.");
                    }

                    escolha = 1;
                }

            }
        } while (escolha != 0);
    }

    // 3. Listar todos os veiculos cadastrados
    private static void buscarTodos() {
        System.out.println("\nLista de veiculos cadastrados:\n" + VeiculoDAO.buscarVeiculos());
    }

    // 4. Listar veiculo pelo ID
    private static void buscarPorId() {
        System.out.print("\nDigite o código do veiculo: ");
        Veiculo Veiculo = VeiculoDAO.buscarVeiculoPorId(input.nextInt());
        input.nextLine();

        if (Veiculo != null) {
            System.out.println(Veiculo);
        } else {
            System.out.println("Código não encontrado.");
        }
    }

    // 5. Listar veiculo(s) pelo nome
    private static void buscarPorAno() {
        System.out.print("Digite o ano do veiculo: ");
        Integer ano = input.nextInt();
        System.out.println("Pesquisa: " + ano);
        List<Veiculo> Veiculos = VeiculoDAO.buscarVeiculosPorAno(Integer.toString(ano));

        if (Veiculos.isEmpty()) {
            System.out.println("Não foram encontrados registros correspondentes para: " + ano);
        } else {
            System.out.println(Veiculos);
        }
    }

    // 6. Deletar um veiculo
    private static void deletar() {
        System.out.print("Digite o código do veiculo para deletar: ");
        Integer id = input.nextInt();
        Boolean result = VeiculoDAO.deletarVeiculo(id);

        if (result) {
            System.out.println("Veiculo com o código " + id + " deletado com sucesso.");
        } else {
            System.out.println("Código não encontrado.");
        }
    }
}
