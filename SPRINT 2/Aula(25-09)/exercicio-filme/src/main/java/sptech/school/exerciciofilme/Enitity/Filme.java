package sptech.school.exerciciofilme.Enitity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;

import java.time.LocalDate;

@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String diretor;

    private LocalDate dataLancamento;

    private Double custoProducao;

    private Boolean indicacaoOscar;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return nome;
    }

    public void setTitulo(String nome) {
        this.nome = nome;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Double getCustoProducao() {
        return custoProducao;
    }

    public void setCustoProducao(Double custoProducao) {
        this.custoProducao = custoProducao;
    }

    public Boolean getIndicacaoOscar() {
        return indicacaoOscar;
    }

    public void setIndicacaoOscar(Boolean indicacaoOscar) {
        this.indicacaoOscar = indicacaoOscar;
    }
}
