package Controllers;

import Models.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MercadoController {

    public static Double getPrecoTotalProduto(Item item) {
        return item.getProduto().getPreco() * item.getQuantidade();
    }

    public static Double getPrecoTotalItens(List<Item> itensList) {
        Double totalItens = 0.0;

        for (int i=0; i < itensList.size(); i++) {
            totalItens += getPrecoTotalProduto(itensList.get(i));
        }

        return totalItens;
    }

    public static void reduzirEstoque(List<Item> itensList) {
        for (int i=0; i < itensList.size(); i++) {
            int quantidadeAtual = itensList.get(i).getProduto().getQuantidade();
            int quantidadeVendida = itensList.get(i).getQuantidade();
            itensList.get(i).getProduto().setQuantidade(quantidadeAtual - quantidadeVendida);
        }

        return;
    }

    public static void imprimirEstoque(List<Produto> produtos) {
        for (int i=0; i < produtos.size(); i++) {
            System.out.println(produtos.get(i).getQuantidade());
        }
    }

    public static void main(String[] args) {
        // Venda 1 ---------------------------------------------
        // Definir vendedor
        Vendedor vendedor1 = new Vendedor("Pelotas");

        // Definir fornecedores
        Fornecedor fornecedor1 = new Fornecedor("432424234234", "53984267467", "Eduardo");
        Fornecedor fornecedor2 = new Fornecedor("343847384378", "53984263742", "Jose");

        // Definir produtos
        Produto produto1 = new Produto(1, "Pizza", 20, 34.50, fornecedor1);
        Produto produto2 = new Produto(2, "Leite Desnatado", 15, 6.50, fornecedor2);
        Produto produto3 = new Produto(3, "Leite Integral", 15, 4.50, fornecedor2);

        // Definir itens
        Item item1 = new Item(1, 0.0, 10, produto1);
        Item item2 = new Item(2, 0.0, 4, produto2);
        Item item3 = new Item(3, 0.0,5, produto3);

        // Definir preço total de cada item
        Double totalPrecoItem1 = getPrecoTotalProduto(item1);
        Double totalPrecoItem2 = getPrecoTotalProduto(item2);
        Double totalPrecoItem3 = getPrecoTotalProduto(item3);

        // Definir preço total dos itens (pedido)
        Double totalPedido1 = getPrecoTotalItens(List.of(item1, item2, item3));

        // Venda 2 ---------------------------------------
        // Definir vendedor
        Vendedor vendedor2 = new Vendedor("Sao Paulo");

        // Definir fornecedores
        Fornecedor fornecedor3 = new Fornecedor("53236236326", "2342343433", "Maria");
        Fornecedor fornecedor4 = new Fornecedor("3482982589", "2598293823", "Marcelo");

        // Definir produtos
        Produto produto4 = new Produto(4, "Coca-Cola", 100, 5.0, fornecedor3);
        Produto produto5 = new Produto(5, "Suco de Laranja", 5, 8.0, fornecedor3);
        Produto produto6 = new Produto(6, "Suco de Uva", 25, 10.0, fornecedor3);
        Produto produto7 = new Produto(7, "Guarana", 90, 4.0, fornecedor3);
        Produto produto8 = new Produto(8, "Carne", 35, 21.0, fornecedor4);

        // Definir itens
        Item item4 = new Item(4, 0.0, 25, produto4);
        Item item5 = new Item(5, 0.0, 3, produto5);
        Item item6 = new Item(6, 0.0, 10, produto6);
        Item item7 = new Item(7, 0.0, 25, produto7);
        Item item8 = new Item(8, 0.0, 30, produto8);

        // Definir preço total de cada item
        Double totalPrecoItem4 = getPrecoTotalProduto(item4);
        Double totalPrecoItem5 = getPrecoTotalProduto(item5);
        Double totalPrecoItem6 = getPrecoTotalProduto(item6);
        Double totalPrecoItem7 = getPrecoTotalProduto(item7);
        Double totalPrecoItem8 = getPrecoTotalProduto(item8);

        // Definir preço total dos itens (pedido)
        Double totalPedido2 = getPrecoTotalItens(List.of(item4, item5, item6, item7, item8));

        // Vendas efetuadas e diminuir estoque ---------------------------------------
        Pedido pedido1 = new Pedido(1, new Date(), totalPedido1, "atendido", List.of(item1, item2, item3), vendedor1);
        Pedido pedido2 = new Pedido(2, new Date(), totalPedido2, "atendido", List.of(item4, item5, item6, item7, item7, item8), vendedor2);

        List<Pedido> pedidosList = List.of(pedido1, pedido2);
        System.out.println("Lista de pedidos:");
        System.out.println(pedidosList);

        System.out.println("Estoque antes da venda:");
        imprimirEstoque(List.of(produto1, produto2, produto3, produto4, produto5, produto6, produto7, produto8));

        reduzirEstoque(List.of(item1, item2, item3, item4, item5, item6, item7, item8));

        System.out.println("Estoque depois da venda:");
        imprimirEstoque(List.of(produto1, produto2, produto3, produto4, produto5, produto6, produto7, produto8));

        // Fornecimentos -----------------------
        int quantidadeFornecimento1 = 30;
        produto1.setQuantidade(produto1.getQuantidade() + quantidadeFornecimento1);
        Double valorTotalFornecimento1 = quantidadeFornecimento1 * produto1.getPreco();
        Fornecimento fornecimento1 = new Fornecimento(new Date(), valorTotalFornecimento1, fornecedor1, produto1);

        int quantidadeFornecimento2 = 20;
        produto2.setQuantidade(produto2.getQuantidade() + quantidadeFornecimento2);
        Double valorTotalFornecimento2 = quantidadeFornecimento2 * produto2.getPreco();
        Fornecimento fornecimento2 = new Fornecimento(new Date(), valorTotalFornecimento2, fornecedor2, produto2);

        List<Fornecimento> fornecimentos = List.of(fornecimento1, fornecimento2);

        Double totalFornecimentos = 0.0;
        for (int i=0; i < fornecimentos.size(); i++) {
            totalFornecimentos += fornecimentos.get(i).getValorTotal();
        }

        System.out.println("Fornecimentos:");
        System.out.println(fornecimentos);
        System.out.println("Valor total dos fornecimentos");
        System.out.println(totalFornecimentos);

    }
}
