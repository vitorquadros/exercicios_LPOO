package Controller;

import java.util.Scanner;

public class MainController {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int escolha = 0;

        do {
            System.out.print("\n------- Home -------");
            System.out.print(
                    "\n1. Manter Usuários" +
                            "\n2. Manter Motoristas" +
                            "\n3. Manter Veículos" +
                            "\n4. Manter Corridas" +
                            "\n\n0. Finalizar\n\n> ");
            escolha = input.nextInt();
            input.nextLine();
            switch (escolha) {
                case 1:
                    UsuarioController.main(null);
                    break;
                case 2:
                    MotoristaController.main(null);
                    break;
                case 3:
                    VeiculoController.main(null);
                    break;
                case 4:
                    CorridaController.main(null);
                    break;
                default:
                    if (escolha != 0) System.out.println("Opção inválida.");
            }
        } while (escolha != 0);

        System.out.println("Aplicação finalizada.");
        input.close();
    }
}