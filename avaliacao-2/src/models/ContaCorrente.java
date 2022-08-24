package models;

public class ContaCorrente extends Conta implements AssociadoVip {

    public ContaCorrente(double saldo) {
        super(saldo);
    }

    public ContaCorrente() {
        super();
    }

    @Override
    public void lucros(int qtdCotas, double valorCota) {
        this.saldo += qtdCotas * valorCota;
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "saldo=" + saldo +
                '}';
    }

}
