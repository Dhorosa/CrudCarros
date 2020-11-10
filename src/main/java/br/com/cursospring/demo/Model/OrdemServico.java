package br.com.cursospring.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor

@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Motorista motorista;
    @ManyToOne
    private Carro carro;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StatusOrdemServico status;

    private String descricao;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime abertura;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime finalizacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrdemServico)) return false;
        OrdemServico that = (OrdemServico) o;
        return getId().equals(that.getId()) &&
                getMotorista().equals(that.getMotorista()) &&
                getCarro().equals(that.getCarro()) &&
                getStatus() == that.getStatus() &&
                getDescricao().equals(that.getDescricao()) &&
                getAbertura().equals(that.getAbertura()) &&
                getFinalizacao().equals(that.getFinalizacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMotorista(), getCarro(), getStatus(), getDescricao(), getAbertura(), getFinalizacao());
    }



    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public StatusOrdemServico getStatus() {
        return status;
    }

    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getAbertura() {
        return abertura;
    }

    public void setAbertura(LocalDateTime abertura) {
        this.abertura = abertura;
    }

    public LocalDateTime getFinalizacao() {
        return finalizacao;
    }

    public void setFinalizacao(LocalDateTime finalizacao) {
        this.finalizacao = finalizacao;
    }



}
