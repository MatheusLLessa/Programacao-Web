package school.sptech.estoque01222143matheuslessa.Entity;

public class Produto {
    private String nome;
    private Double preco;
    private Integer qtdEstoque;
    private Double valorTotalEstoque;

    public Produto(String nome, Double preco, Integer qtdEstoque, Double valorTotalEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.valorTotalEstoque = preco * qtdEstoque;
    }

    public Produto(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Double getValorTotalEstoque() {
        return preco * qtdEstoque;
    }




}
