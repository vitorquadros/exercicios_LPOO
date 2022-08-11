package models;

public class ContaPoupanca extends Conta {
    public ContaPoupanca() {
        super();
    }

    public ContaPoupanca(double saldo) {
        super(saldo);
    }

    @Override
    public String toString() {
        return "ContaPoupanca{" +
                "saldo=" + saldo +
                '}';
    }
}
