package controllers;

import models.*;

import java.util.ArrayList;
import java.util.List;

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
//        System.out.println(contaPoupanca1);
//        System.out.println(contaCorrente1);
//        System.out.println(associado1);

        // b)
        List<Conta> contasLista = new ArrayList();
        List<Associado> associadosLista = new ArrayList();

        contasLista.add(contaPoupanca1);
        contasLista.add(contaPoupanca2);
        contasLista.add(contaPoupanca3);
        contasLista.add(contaCorrente1);
        contasLista.add(contaCorrente2);
        contasLista.add(contaCorrente3);

        associadosLista.add(associado1);
        associadosLista.add(associado2);
        associadosLista.add(associado3);

//        System.out.println(contasLista);
//        System.out.println(associadosLista);

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

        System.out.println(contasLista);
        System.out.println(associadosLista);
    }
}
