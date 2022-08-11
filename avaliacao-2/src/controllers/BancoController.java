package controllers;

import models.Associado;
import models.ContaCorrente;
import models.ContaPoupanca;

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
        Associado associado2 = new Associado("Lucas");
        Associado associado3 = new Associado("Pedro");

        // a)
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

        System.out.println(contasPoupancaLista);

    }
}
