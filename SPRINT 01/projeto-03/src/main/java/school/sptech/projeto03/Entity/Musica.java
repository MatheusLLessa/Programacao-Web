package school.sptech.projeto03.Entity;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Musica {
    private String nome;
    private String artista;

    /*
    Tipos de DateTime
    LocalDate -> */

    private LocalDate dataCriacao = LocalDate.now();

    public Musica(String nome, String artista) {
        this.nome = nome;
        this.artista = artista;
    }
    public Musica(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
