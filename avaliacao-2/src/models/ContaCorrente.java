package models;

public class ContaCorrente extends Conta implements AssociadoVip {

    public ContaCorrente(double saldo) {
        super(saldo);
    }

    public ContaCorrente() {
        super();
    }

    @Override
    public double lucros(int qtdCotas, double valorCota) {
        return 0;
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "saldo=" + saldo +
                '}';
    }
}
