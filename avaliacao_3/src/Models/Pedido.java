package Models;

import java.util.Date;
import java.util.List;

public class Pedido {
    private Integer numero;
    private Date data;
    private Double valor;

    Vendedor vendedor;
    List<Item> itensList;

    public Pedido(Integer numero, Date data, Double valor, List<Item> itensList, Vendedor vendedor) {
        this.numero = numero;
        this.data = data;
        this.valor = valor;
        this.itensList = itensList;
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numero=" + numero +
                ", data=" + data +
                ", valor=" + valor +
                ", vendedor=" + vendedor +
                ", itensList=" + itensList +
                '}';
    }
}
