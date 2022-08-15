package controllers;

import models.Associado;
import models.ContaCorrente;
import models.ContaPoupanca;

import java.util.ArrayList;
import java.util.List;

public class BancoController {
    public static void main(String[] args) {

        // a)
        ContaPoupanca contaPoupanca1 = new ContaPoupanca(2500);
        ContaPoupanca contaPoupanca2 = new ContaPoupanca(100);
        ContaPoupanca contaPoupanca3 = new ContaPoupanca();

        ContaCorrente contaCorrente1 = new ContaCorrente(10500);
        ContaCorrente contaCorrente2 = new ContaCorrente(100);
        ContaCorrente contaCorrente3 = new ContaCorrente();

        Associado associado1 = new Associado("Joao");
        Associado associado2 = new Associado("Lucas");
        Associado associado3 = new Associado("Pedro");


//        System.out.println(contaPoupanca1);
//        System.out.println(contaCorrente1);
//        System.out.println(associado1);

        // b)
        List<ContaPoupanca> contasPoupancaLista = new ArrayList();
        List<ContaCorrente> contasCorrenteLista = new ArrayList();
        List<Associado> associadosLista = new ArrayList();

        contasPoupancaLista.add(contaPoupanca1);
        contasPoupancaLista.add(contaPoupanca2);
        contasPoupancaLista.add(contaPoupanca3);

        contasCorrenteLista.add(contaCorrente1);
        contasCorrenteLista.add(contaCorrente2);
        contasCorrenteLista.add(contaCorrente3);

        associadosLista.add(associado1);
        associadosLista.add(associado2);
        associadosLista.add(associado3);

        // System.out.println(contasPoupancaLista);
        // System.out.println(associadosLista);

        // c)
        contaPoupanca1.deposita(1000.00);
        // System.out.println("Depositado R$ 1000 - " + contaPoupanca1);
        contaPoupanca1.atualiza(5);
        // System.out.println("Atualizado com taxa de 5% - " + contaPoupanca1);
        contaPoupanca1.saca(50.00);
        // System.out.println("Sacado R$ 50 - " + contaPoupanca1);

        // d)
        contaCorrente1.deposita(500.00);
        // System.out.println("Depositado R$ 500 - " + contaCorrente1);
        contaCorrente1.saca(400.00);
        // System.out.println("Sacado R$ 400 - " + contaCorrente1);

        // e)

    }
}
