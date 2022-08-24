package controllers;

import models.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BancoController {
    public static void main(String[] args) {

        ContaPoupanca contaPoupanca1 = new ContaPoupanca(2500.50);
        ContaPoupanca contaPoupanca2 = new ContaPoupanca(100);
        ContaPoupanca contaPoupanca3 = new ContaPoupanca();

        ContaCorrente contaCorrente1 = new ContaCorrente(10500.50);
        ContaCorrente contaCorrente2 = new ContaCorrente(100);
        ContaCorrente contaCorrente3 = new ContaCorrente();

        Associado associado1 = new Associado("Joao");
        Associado associado2 = new Associado("Pedro");
        Associado associado3 = new Associado("Ana");

        // a)
        System.out.println("Conta poupança 1: " + contaPoupanca1);
        System.out.println("Conta corrente 1: " + contaCorrente1);
        System.out.println("Associado 1: " + associado1);

        // b)
        List<Conta> contasLista = new ArrayList<>();
        List<Associado> associadosLista = new ArrayList<>();

        contasLista.add(contaPoupanca1);
        contasLista.add(contaPoupanca2);
        contasLista.add(contaPoupanca3);
        contasLista.add(contaCorrente1);
        contasLista.add(contaCorrente2);
        contasLista.add(contaCorrente3);

        associadosLista.add(associado1);
        associadosLista.add(associado2);
        associadosLista.add(associado3);

        System.out.println("Lista de contas: " + contasLista);
        System.out.println("Lista de associados: " + associadosLista);

        // c)
        contaPoupanca1.deposita(1000.0);
        contaPoupanca1.atualiza(5);
        contaPoupanca1.saca(50.0);

        // d)
        contaCorrente1.deposita(500);
        contaCorrente1.saca(400.0);

        // e)
        associado1.lucros(100, 250.0);
        associado2.lucros(150, 250.0);
        associado3.lucros(40, 250.0);
        contaCorrente1.lucros(310, 250.0);
        contaCorrente2.lucros(20, 250.0);
        contaCorrente3.lucros(80, 250.0);

         System.out.println("Lista de contas com os valores das cotas dos associados VIP: " + contasLista);
         System.out.println("Lista de associados com o numero de cotas: " + associadosLista);

        // f)
        associadosLista.sort(Comparator.comparing(Associado::getCotas).reversed()); // decrescente
         System.out.println("Associados em ordem de cotas decrescente: " + associadosLista);

         System.out.println("Associado com maior numero de cotas: " + associadosLista.get(0));

        // g)
        contasLista.sort(Comparator.comparing(Conta::getSaldo).reversed()); // decrescente
        System.out.println("Contas com saldo em ordem decrescente: " + contasLista);

        List<Conta> associadosVipContas = new ArrayList<>();
        List<Conta> contasNormal = new ArrayList<>();

        contasLista.forEach(conta -> { // associados vip que tenham conta
            if (conta instanceof AssociadoVip) {
                associadosVipContas.add(conta);
            } else contasNormal.add(conta);
        });

        System.out.println("Associados VIP que tem conta: " + associadosVipContas);

        double saldoCorrente = 0;
        double saldoPoupanca = 0;

        for(int i=0; i < contasLista.size(); i++) {
            Conta conta = contasLista.get(i);

            if (conta instanceof ContaCorrente) {
                saldoCorrente += conta.getSaldo();
            } else {
                saldoPoupanca += conta.getSaldo();
            }
        }

        // System.out.println(saldoCorrente);
        // System.out.println(saldoPoupanca);

        if (saldoCorrente > saldoPoupanca) {
            System.out.println("Lista com maior saldo: " + associadosVipContas); // lista de contas com maior saldo
        } else {
            System.out.println("Lista com maior saldo: " + contasNormal);
        }
    }
}
