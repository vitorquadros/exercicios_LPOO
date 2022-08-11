package models;

public class Associado implements AssociadoVip {
    private String nome;

    @Override
    public double lucros(int qtdCotas, double valorCota) {
        return 0;
    }
}
