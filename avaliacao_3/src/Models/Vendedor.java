
package Models;

import java.util.List;

public class Vendedor extends Funcionario {
    private String local;

    public Vendedor(String local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "local='" + local + '\'' +
                '}';
    }
}