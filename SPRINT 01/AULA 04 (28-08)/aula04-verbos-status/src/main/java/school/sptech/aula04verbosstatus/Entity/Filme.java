package school.sptech.aula04verbosstatus.Entity;

import java.math.BigDecimal;

public class Filme {

    private String nome;
    private String diretor;
    private int anoLancamento;
    private BigDecimal custoProducao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public BigDecimal getCustoProducao() {
        return custoProducao;
    }

    public void setCustoProducao(BigDecimal custoProducao) {
        this.custoProducao = custoProducao;
    }
}
