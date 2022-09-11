package Controller;

import DAO.CorridaDAO;
import Model.Corrida;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CorridaController {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int escolha = 0;

        do {
            System.out.print("\n\"-------  MENU Corrida -------\"");
            System.out.println("\n> Selecione uma opção");
            System.out.print(
                    "\n1. Cadastrar nova corrida" +
                            "\n2. Atualizar corrida existente" +
                            "\n3. Listar todas as corridas" +
                            "\n4. Listar corrida pelo ID" +
                            "\n5. Listar corrida(s) pela data de ínicio" +
                            "\n6. Deletar uma corrida\n" +
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
                    buscarPorData();
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

    // 1. Cadastrar novo corrida
    private static void inserir() {
        Corrida corrida = new Corrida();
        System.out.println("\n++++++ Cadastro de nova Corrida ++++++");
        System.out.print("Digite o tipo de pagamento da corrida: ");
        corrida.setTipoPagamento(input.nextLine());
        System.out.print("\nDigite os detalhes de pagamento da corrida: ");
        corrida.setDetalhesPagamento(input.nextLine());
        System.out.print("\nDigite o preço da corrida: ");
        corrida.setPreco(input.nextDouble());
        System.out.print("\nDigite o código do usuário da corrida: ");
        corrida.setUsuarioId(input.nextInt());
        System.out.print("\nDigite o código do motorista da corrida: ");
        corrida.setMotoristaId(input.nextInt());

        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date hoje = Calendar.getInstance().getTime();
        corrida.setDataInicio(df.format(hoje));

        if (CorridaDAO.criarCorrida(corrida)) {
            System.out.println("\nCorrida salva com sucesso.");
        } else {
            System.out.println("\nOcorreu um erro.");
        }
    }

    // 2. Atualizar corrida já existente
    private static void atualizar() {
        System.out.println("\n++++++ Atualizar dados do corrida ++++++");
        Corrida corrida = null;
        int escolha = 0;

        do {
            System.out.print("\nEscolha o corrida (código) para atualizar os dados (0 para sair): ");
            int codigo = input.nextInt();
            input.nextLine();

            if (codigo == 0) {
                escolha = 0;
            } else {
                corrida = CorridaDAO.buscarCorridaPorId(codigo);

                if (corrida == null) {
                    System.out.println("Código inválido.");
                } else {
                    System.out.println("Tipo pagamento: " + corrida.getTipoPagamento());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.println("Digite o novo tipo de pagamento da corrida: ");
                        corrida.setTipoPagamento(input.nextLine());
                    }

                    System.out.println("Detalhes pagamento: " + corrida.getDetalhesPagamento());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.print("Digite os novos detalhes de pagamento da corrida: ");
                        corrida.setDetalhesPagamento(input.next());
                    }

                    System.out.println("Preço: " + corrida.getPreco());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.print("Digite o novo preço da corrida: ");
                        corrida.setPreco(input.nextDouble());
                    }

                    System.out.println("Usuário da corrida: " + corrida.getUsuarioId());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.print("Digite o novo código do usuário da corrida: ");
                        corrida.setUsuarioId(input.nextInt());
                    }

                    System.out.println("Motorista da corrida: " + corrida.getMotoristaId());
                    System.out.print("Atualizar? (0 - Sim / 1 - Não) ");

                    if (input.nextInt() == 0) {
                        input.nextLine();
                        System.out.print("Digite o novo código do motorista da corrida: ");
                        corrida.setMotoristaId(input.nextInt());
                    }

                    if (CorridaDAO.atualizarCorrida(corrida)) {
                        System.out.println("Novos dados salvos: " + corrida);
                    } else {
                        System.out.println("Ocorreu um erro.");
                    }

                    escolha = 1;
                }

            }
        } while (escolha != 0);
    }

    // 3. Listar todas as corridas cadastradas
    private static void buscarTodos() {
        System.out.println("\nLista de corridas cadastrados:\n" + CorridaDAO.buscarCorridas());
    }

    // 4. Listar corrida pelo ID
    private static void buscarPorId() {
        System.out.print("\nDigite o código do corrida: ");
        Corrida Corrida = CorridaDAO.buscarCorridaPorId(input.nextInt());
        input.nextLine();

        if (Corrida != null) {
            System.out.println(Corrida);
        } else {
            System.out.println("Código não encontrado.");
        }
    }

    // 5. Listar corrida(s) pela data
    private static void buscarPorData() {
        System.out.print("Digite a data de ínicio da corrida: ");
        String data = input.next();
        System.out.println("Pesquisa: " + data);
        List<Corrida> corridas = CorridaDAO.buscarCorridasPorData(data);

        if (corridas.isEmpty()) {
            System.out.println("Não foram encontrados registros correspondentes para: " + data);
        } else {
            System.out.println(corridas);
        }
    }

    // 6. Deletar uma corrida
    private static void deletar() {
        System.out.print("Digite o código da corrida para deletar: ");
        Integer id = input.nextInt();
        Boolean result = CorridaDAO.deletarCorrida(id);

        if (result) {
            System.out.println("Corrida com o código " + id + " deletada com sucesso.");
        } else {
            System.out.println("Código não encontrado.");
        }
    }
}


