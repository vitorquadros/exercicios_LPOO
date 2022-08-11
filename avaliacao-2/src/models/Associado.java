package models;

public class Associado implements AssociadoVip {
    private final String nome;

    public Associado(String nome) {
        this.nome = nome;
    }

    @Override
    public double lucros(int qtdCotas, double valorCota) {
        return 0;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Associado{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
