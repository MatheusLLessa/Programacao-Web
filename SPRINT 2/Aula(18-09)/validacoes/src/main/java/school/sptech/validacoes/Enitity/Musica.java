package school.sptech.validacoes.Enitity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
@Entity // Indica que uma classe na tabela do banco de dados
public class Musica {

    @Id // Este ID vai indicar convertido como chave primaria no banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica uma chave primaria ou auto_increment no BD
    private Integer id;

    @NotBlank
    private String nome;

    @NotBlank
    @Size(min = 3, max = 30)
    // Size (serve só pra alfanumerico String)
        //(MIN = 3)
        //(MAX = 30)
    private String album;


    @CNPJ
    private String cnpjProdutora;


    @CPF
    private String cpfProdutora;

    @Email // Validação só com @
    private String email;

    @PastOrPresent // Datas passadas e presente
    // @Past // Datas passadas
    // @Future // Datas futuras
    // @FutureOrPresent // Datas futura e presente
    private LocalDate dataLancamento;


    @DecimalMin(value = "1.0")
    @DecimalMax(value = "5.0")
    private Double nota;


    @Min(1)
    private Integer ranking;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}
