package Model;

public class Veiculo {
    private Integer id;
    private String tipo;
    private String placa;
    private String anoFabricacao;

    private Motorista motorista;

    public Veiculo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(String anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", placa='" + placa + '\'' +
                ", anoFabricacao='" + anoFabricacao + '\'' +
                ", motorista=" + motorista +
                '}';
    }
}
