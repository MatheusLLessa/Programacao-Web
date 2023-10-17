package sptech.school.exerciciofilme.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.exerciciofilme.Enitity.Filme;
import sptech.school.exerciciofilme.Repository.FilmeRepository;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

            // OU

    // private final FilmeRepository repository
    // public FilmeContoller(FilmeRepository repository)
    //      this.repository = repository

    // POST /filmes -> cadastra um filme;
    @PostMapping
    public ResponseEntity<Filme> cadastrar(@RequestBody @Valid Filme novoFilme){
        Filme filmeCadastrado = this.filmeRepository.save(novoFilme);
        return ResponseEntity.status(201).body(filmeCadastrado);
        // return ResponseEntity.createrd(null).body(filmeCadastrado);
    }

    // GET /filmes -> listagem de filmes;
    @GetMapping
    public ResponseEntity<List<Filme>> listar(){
        List<Filme> filmes = filmeRepository.findAll();
        if (filmes.isEmpty()){
            return ResponseEntity.status(204).build();
            // return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(filmes);
        // return ResponseEntity.ok.(filmes);
    }

    // GET /filmes/{id} -> buscar um filme por ID;
    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPeloId(@PathVariable Integer id){
        return ResponseEntity.of(this.filmeRepository.findById(id));
    }

    // PUT /filmes/{id} -> Atualiza um filme por ID;
    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizar(@PathVariable Integer id, @RequestBody @Valid Filme filme){
        filme.setId(id);

        if(this.filmeRepository.existsById(id)){
            Filme filmeAtulaizado = this.filmeRepository.save(filme);
            return ResponseEntity.status(200).body(filmeAtulaizado);
        }
        return ResponseEntity.status(404).build();
    }


    // DELETE /filmes/{id} -> Remove um filme por ID;
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id){
        if(this.filmeRepository.existsById(id)){
            this.filmeRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<Filme>> buscarPorNome(@RequestParam String nome){
        List<Filme> listagem = this.filmeRepository.findByNomeContainingIgnoreCase(nome);
        if(listagem.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listagem);
    }

    @GetMapping("/diretor")
    public ResponseEntity<List<Filme>> buscarPorDiretor(@RequestParam String diretor){
        List<Filme> listagem = this.filmeRepository.findByDiretorContains(diretor);
        if(listagem.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listagem);
    }

    @GetMapping("/date")
    public ResponseEntity<List<Filme>> buscarPorData(@RequestParam LocalDate date){
        List<Filme> listagem = this.filmeRepository.findByDataLancamentoLessThanEqual(date);
        if(listagem.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listagem);
    }

    @GetMapping("/indicacaoOscar")
    public ResponseEntity<List<Filme>> buscarPorIndicacaoOscar(){
        List<Filme> listagem = this.filmeRepository.findByIndicacaoOscarTrue();
        if(listagem.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listagem);
    }


    @GetMapping("/indicacaoOscar")
    public ResponseEntity<List<Filme>> buscarPorNaoIndicacaoOscar(){
        List<Filme> listagem = this.filmeRepository.findByIndicacaoOscarFalse();
        if(listagem.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listagem);
    }
}
