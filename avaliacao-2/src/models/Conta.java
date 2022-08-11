package models;

public abstract class Conta {

    public Conta(double saldo) {
        this.saldo = saldo;
    }

    public Conta() {
    }

    protected double saldo;

    public void deposita(double valor) {
        this.saldo += valor;
    }

    public void saca(double valor) {
        this.saldo -= valor;
    }

    public void atualiza(double taxa) {
        this.saldo = this.saldo * (taxa / 100);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "saldo=" + saldo +
                '}';
    }
}
