package Model;

import java.util.Date;

public class Corrida {

    private Integer id;
    private String tipoPagamento;
    private String detalhesPagamento;
    private String dataInicio;
    private Double preco;

    private Integer usuarioId;
    private Integer motoristaId;

    public Corrida() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getDetalhesPagamento() {
        return detalhesPagamento;
    }

    public void setDetalhesPagamento(String detalhesPagamento) {
        this.detalhesPagamento = detalhesPagamento;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getMotoristaId() {
        return motoristaId;
    }

    public void setMotoristaId(Integer motoristaId) {
        this.motoristaId = motoristaId;
    }

    @Override
    public String toString() {
        return "Corrida{" +
                "id=" + id +
                ", tipoPagamento='" + tipoPagamento + '\'' +
                ", detalhesPagamento='" + detalhesPagamento + '\'' +
                ", dataInicio=" + dataInicio +
                ", preco=" + preco +
                ", usuarioId=" + usuarioId +
                ", motoristaId=" + motoristaId +
                '}';
    }
}