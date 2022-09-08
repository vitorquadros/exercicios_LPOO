package Models;

import java.util.Date;
import java.util.List;

public class Pedido {
    private Integer numero;
    private Date data;
    private Double valor;
    private String tipo = "pendente";

    Vendedor vendedor;
    List<Item> itensList;

    public Pedido(Integer numero, Date data, Double valor, String tipo, List<Item> itensList, Vendedor vendedor) {
        this.numero = numero;
        this.data = data;
        this.valor = valor;
        this.itensList = itensList;
        this.vendedor = vendedor;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numero=" + numero +
                ", data=" + data +
                ", valor=" + valor +
                ", tipo='" + tipo + '\'' +
                ", vendedor=" + vendedor +
                ", itensList=" + itensList +
                '}';
    }
}