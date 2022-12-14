package Models;

import Exceptions.EstoqueInsuficienteException;

import java.util.List;

public class Item {
    private Integer codItem;
    private Double desconto;
    private Integer quantidade;

    Produto produto;

    public Item(Integer codItem, Double desconto, Integer quantidade, Produto produto) throws EstoqueInsuficienteException {
        if (quantidade > produto.getQuantidade()) throw new EstoqueInsuficienteException();
        this.codItem = codItem;
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "Item{" +
                "codItem=" + codItem +
                ", desconto=" + desconto +
                ", quantidade=" + quantidade +
                ", produto=" + produto +
                '}';
    }
}