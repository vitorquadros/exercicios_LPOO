package Models;

public class Fornecedor {
    private String cnpj;
    private String contato;
    private String nome;

    public Fornecedor(String cnpj, String contato, String nome) {
        this.cnpj = cnpj;
        this.contato = contato;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "cnpj='" + cnpj + '\'' +
                ", contato='" + contato + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
