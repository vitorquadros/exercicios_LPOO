package models;

public class Associado implements AssociadoVip {
    private final String nome;
    private double cotas;

    public Associado(String nome) {
        this.nome = nome;
    }

    @Override
    public void lucros(int qtdCotas, double valorCota) {
        this.cotas = qtdCotas;
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
