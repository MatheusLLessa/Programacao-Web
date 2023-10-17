package sptech.school.livraria.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sptech.school.livraria.Enitity.Livro;
import sptech.school.livraria.Service.LivroService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

public class LivroController {
    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Livro> cadastrar(@Valid @RequestBody Livro novoLivro){
        Livro livroSalvo = this.service.salvar(novoLivro);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livroSalvo.getId())
                .toUri();
        return ResponseEntity.created(uri).body(livroSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listar(){
        List<Livro> livros = this.service.listar();
        if (livros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> listarPorId(@PathVariable Long id){
        Livro livroEncontrado = this.service.buscarPorId(id);
        return ResponseEntity.ok(livroEncontrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarPorId(@PathVariable Long id, @Valid @RequestBody Livro livroAtualizado){
        Livro livroatualizado = this.service.atualizarPorId(id, livroAtualizado);
        return ResponseEntity.ok(livroatualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        this.service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/titulo/autor")
    public ResponseEntity<Void> atualizarTituloAutor(@PathVariable Long id,
                                                     @RequestParam String titulo,
                                                     @RequestParam String autor){
        this.service.atualizarPorId(id, titulo, autor);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nao-publicado")
    public ResponseEntity<List<Livro>> buscarNaoPublicado(@RequestParam LocalDate data){
        List<Livro> livros = this.service.buscarNaoPublicado(data);
        if (livros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/top5")
    public ResponseEntity<List<Livro>> buscarOs5AteDataInformada(LocalDate dataInformada) {

        List<Livro> ateDataInformada = this.service.buscarOs5AteDataInformada(dataInformada);

        if (ateDataInformada.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ateDataInformada);
    }

    @GetMapping("/mais-caro")
    public ResponseEntity<Livro> maiorCusto() {
        Livro livro = this.service.maiorCusto();
        return ResponseEntity.ok(livro);
    }
}
