package exercicio.preparacaoprova.controller;

import exercicio.preparacaoprova.entity.Musica;
import exercicio.preparacaoprova.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/musicas")
public class MusicaController {
    @Autowired
    private  MusicaRepository musicaRepository;

    //Enpoint adicional
    @GetMapping
    public ResponseEntity<List<Musica>> listagemGeral() {
        List<Musica> musicas = this.musicaRepository.findAll();
        if(musicas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(musicas);
    }

    //Enpoint adicional
    @GetMapping("/{id}")
    public ResponseEntity<Musica> buscarPorId(@PathVariable UUID id) {
        Optional<Musica> musicaOpt = this.musicaRepository.findById(id);
        return ResponseEntity.of(musicaOpt);
    }

    @GetMapping("/artista")
    public ResponseEntity<List<Musica>> buscarPorArtista(@RequestParam String nome) {
        List<Musica> musicas = this.musicaRepository.findByArtista(nome);

        if(musicas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(musicas);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Musica>> buscarPorTitulo(@RequestParam String titulo) {
        List<Musica> musicas = this.musicaRepository.findByNomeContainingIgnoreCase(titulo);

        if(musicas.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(musicas);
    }

    @GetMapping("/genero/contagem")
    public ResponseEntity<Integer> contarPorGenero(@RequestParam String nome) {
       Integer contage = this.musicaRepository.contarPorGenero(nome);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/nao-lancadas")
    public ResponseEntity<List<Musica>> buscarNaoLancadas() {
        List<Musica> naoLancadas = this.musicaRepository.findByDataLancamentoAfter(LocalDate.now());

        if(naoLancadas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(naoLancadas);
    }

    @GetMapping("/lancadas")
    public ResponseEntity<List<Musica>> buscarLancadas() {
       List<Musica> lancada =  this.musicaRepository.findByDataLancamentoLessThanEqual(LocalDate.now());

       if (lancada.isEmpty()){
           return ResponseEntity.noContent().build();
       }
        return ResponseEntity.ok(lancada);
    }

    @GetMapping("/mais-acessos")
    public ResponseEntity<Musica> buscarMaisPopular() {
        Optional<Musica> popular = this.musicaRepository.findFirstByOrderByAcessosDesc();
        return ResponseEntity.of(popular);
    }

    @GetMapping("/top3-notas")
    public ResponseEntity<List<Musica>> buscarTop3MaioresNotas() {
        List<Musica> melhores = this.musicaRepository.findTop3ByOrderByNotaDesc();
        if(melhores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(melhores);
    }

    @GetMapping("/genero/acessos/contagem")
    public ResponseEntity<Integer> buscarQuantidadeAcessosPorGenero(@RequestParam String nome){
        Integer soma = this.musicaRepository.acessosPorGenero(nome).orElse(0);
        return ResponseEntity.ok(soma);
    }

    @GetMapping("/menor-nota")
    public ResponseEntity<Musica> buscarMenorNota() {
        Optional<Musica> menorNota = this.musicaRepository.menorNota();
        return ResponseEntity.of(menorNota);
    }
}
