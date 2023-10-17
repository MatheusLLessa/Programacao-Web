package school.sptech.projetoormads.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.constraints.br.TituloEleitoral;

import java.time.LocalDate;

/*
  Ao declarar uma classe com @Entity estamos informando ao Spring que essa classe
  reflete uma entity(Tabela) no banco de dados.

  O JPA (Java persistence API) cuidará em trocar os nomes em 'camelCase' para 'snake_case' automaticamente.

  No fim, "1 Musica" representará "1 instância de Musica" automaticamente se o mapeamento estiver correto.
*/

@Entity // jakarta.persistence
public class Musica {

  /*
    As validações podem ser utilizadas em DTO's também!!!
    Ou qualquer outro objeto recebido como corpo da requisição
  */

  @Id // Identificador -> chave primária em nossa tabela
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de indexação, nesse caso 'auto incremento'
  private Integer id;

  /*
    Não permitir:
      null, "" ou "  "
   */
  @NotBlank // permite somente valores diferentes de: null, "" e " "
  @Size(min = 3) // Valida o tamanho do texto, nesse caso min caracteriza mínimo de 3 caracteres pode ser usado com max
  private String nome;

  @NotBlank
  @Size(min = 3, max = 10) // ERRO COMUN: Usar essa validacao para tipos que NÃO SÃO STRING's
  private String album;


  @PastOrPresent // permite apenas datas atuais ou passado
//  @Past - permite apenas datas que antedecem a data atual
//  @Future - permite apenas datas futuras
//  @FutureOrPresent - permite apenas datas futura ou a data atual
  private LocalDate dataLancamento; // ano-mes-dia, exemplo: 1993-06-11

  @DecimalMin(value = "0.5") // Para numeros decimais
  @DecimalMax(value = "8.0") // Para numeros decimais
  private Double duracao;

  //  @Min(1) // Serve para definir tamanho mínimo numéricos inteiros
//  @Max(10) // Serve para definir tamanho máximo de númericos inteiros
  @Positive // permite apenas positivos
//  @PositiveOrZero permite numeros positivos e incluí o zero
//  @Negative permite apenas numeros negativos
//  @NegativeOrZero permite numeros negativos e incluí o zero
  private Integer ranking;

  @Email
  @NotNull
  private String contato;

  /*
    Aceita telefones nos padrões abaixo:

    9999-9999
    99999-9999
    11-9999-9999
    11-99999-9999
    (11)9999-9999
    (11)99999-9999
   */
  @Pattern( // valida usando uma Regex (expressão regular)
          regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})",
          message = "Indique um telefone válido"
  )
  private String telefoneContato;

  @CPF
  @NotNull
  private String cpfContato;

  @TituloEleitoral
  private String tituloEleitorContato;

  @CNPJ
  private String gravadoraCnpj;

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

  public Double getDuracao() {
    return duracao;
  }

  public void setDuracao(Double duracao) {
    this.duracao = duracao;
  }

  public Integer getRanking() {
    return ranking;
  }

  public void setRanking(Integer ranking) {
    this.ranking = ranking;
  }

  public String getContato() {
    return contato;
  }

  public void setContato(String contato) {
    this.contato = contato;
  }

  public String getTelefoneContato() {
    return telefoneContato;
  }

  public void setTelefoneContato(String telefoneContato) {
    this.telefoneContato = telefoneContato;
  }

  public String getCpfContato() {
    return cpfContato;
  }

  public void setCpfContato(String cpfContato) {
    this.cpfContato = cpfContato;
  }

  public String getTituloEleitorContato() {
    return tituloEleitorContato;
  }

  public void setTituloEleitorContato(String tituloEleitorContato) {
    this.tituloEleitorContato = tituloEleitorContato;
  }

  public String getGravadoraCnpj() {
    return gravadoraCnpj;
  }

  public void setGravadoraCnpj(String gravadoraCnpj) {
    this.gravadoraCnpj = gravadoraCnpj;
  }
}
