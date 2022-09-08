package Models;

import java.util.List;

public class Produto {
    private Integer codigo;
    private String nome;
    private Integer quantidade;
    private Double preco;

    Fornecedor fornecedor;

    public Produto(Integer codigo, String nome, Integer quantidade, Double preco, Fornecedor fornecedor) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.fornecedor = fornecedor;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                ", fornecedor=" + fornecedor +
                '}';
    }
}