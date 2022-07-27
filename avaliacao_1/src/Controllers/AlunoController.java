package Controllers;

import Models.Aluno;

import java.util.*;

public class AlunoController {
    public static void main(String[] args) {

        // construtor com todos os atributos
        System.out.println("\nConstrutor com todos os atributos\n");

        Aluno maria = new Aluno(1, "56272647884", "Maria", "Vieira", "maria@hotmail.com");
        System.out.println(maria.toString());

        Aluno ana = new Aluno(2, "547256964783", "Ana", "Brito", "ana@hotmail.com");
        System.out.println(ana.toString());

        // construtor com 2 atributos
        System.out.println("\nConstrutor com 2 atributos\n");

        Aluno jose = new Aluno("José", "Lopes");
        jose.setId(3);
        System.out.println(jose.toString());

        Aluno marcela = new Aluno("Marcela", "Rosa");
        marcela.setId(4);
        System.out.println(marcela.toString());

        // construtor padrão
        System.out.println("\nConstrutor padrão\n");

        Aluno joao = new Aluno();
        Aluno pedro = new Aluno();

        joao.setId(5);
        joao.setNome("João");
        joao.setSobrenome("Lucas");
        joao.setCpf("526642757664");
        joao.setEmail("joao@hotmail.com");

        pedro.setId(6);
        pedro.setNome("Pedro");
        pedro.setSobrenome("Silva");
        pedro.setCpf("426842251769");
        pedro.setEmail("pedro@hotmail.com");

        Aluno[] alunos = {joao, pedro};

        for (int i = 0; i < alunos.length; i++) {
            System.out.println("Aluno " + alunos[i].getId() + ": " + alunos[i].getNome() + " " + alunos[i].getSobrenome());
        }

        // List e Map
        System.out.println("\nList e Map");

        List<Aluno> lista_alunos = new ArrayList<>();
        Map<Integer, Aluno> lista_alunos_map = new HashMap<>();

        lista_alunos.add(maria);
        lista_alunos.add(ana);
        lista_alunos.add(jose);
        lista_alunos.add(marcela);
        lista_alunos.add(joao);
        lista_alunos.add(pedro);

        lista_alunos_map.put(maria.getId(), maria);
        lista_alunos_map.put(ana.getId(), ana);
        lista_alunos_map.put(jose.getId(), jose);
        lista_alunos_map.put(marcela.getId(), marcela);
        lista_alunos_map.put(joao.getId(), joao);
        lista_alunos_map.put(pedro.getId(), pedro);

        // Listas em ordem crescente
        System.out.println("\nListas em ordem crescente\n");

        System.out.println(lista_alunos);
        System.out.println(lista_alunos_map);

        // Aluno com id=5
        System.out.println("\nAluno com id=5\n");

        if (lista_alunos.size() >= 5) {
            System.out.println(lista_alunos.get(5 - 1));
        }

        if (lista_alunos_map.size() >= 5) {
            System.out.println(lista_alunos.get(5 - 1));
        }

        // Lista em ordem decrescente
        System.out.println("\nLista em ordem decrescente\n");

        lista_alunos.sort(Comparator.comparing(Aluno::getId).reversed());
        System.out.println(lista_alunos);

    }
}
