package Models;

import java.util.Date;
import java.util.List;

public class Fornecimento {
    private Date data;
    private Double valorTotal;

    Fornecedor fornecedor;
    Produto produto;

    public Fornecimento(Date data, Double valorTotal, Fornecedor fornecedor, Produto produto) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.fornecedor = fornecedor;
        this.produto = produto;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        return "Fornecimento{" +
                "data=" + data +
                ", valorTotal=" + valorTotal +
                ", fornecedor=" + fornecedor +
                ", produto=" + produto +
                '}';
    }
}