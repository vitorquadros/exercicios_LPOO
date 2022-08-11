package models;

public class ContaCorrente extends Conta {

    public ContaCorrente(double saldo) {
        super(saldo);
    }

    public ContaCorrente() {
        super();
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "saldo=" + saldo +
                '}';
    }
}
