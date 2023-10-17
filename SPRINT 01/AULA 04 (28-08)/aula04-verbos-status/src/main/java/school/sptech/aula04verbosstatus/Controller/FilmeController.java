package school.sptech.aula04verbosstatus.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.aula04verbosstatus.Entity.Filme;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private List<Filme> listaFilmes = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Filme>> listar(){
        if(listaFilmes.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaFilmes);
    }

    // Post - localhost:8080/filmes
    @PostMapping
    public ResponseEntity<Filme> cadastrar(@RequestBody Filme filme){
        listaFilmes.add(filme);
        return ResponseEntity.status(201).body(filme);
    }

    @PutMapping("/{indice}")
    public ResponseEntity<Filme> recuperarId(@PathVariable int indice ,@RequestBody Filme filmeAtualizado){
        if(indice >= 0 && indice < listaFilmes.size()){
            listaFilmes.set(indice, filmeAtualizado);
            return ResponseEntity.status(200).body(filmeAtualizado);
        }
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Filme> deletar(@PathVariable int indice, @RequestBody Filme filmeRemovido){
        if (indice >= 0 && indice < listaFilmes.size()){
            listaFilmes.remove(indice);
            return ResponseEntity.status(200).build();
        }else {
            return ResponseEntity.status(204).build();
        }

    }

    @GetMapping("/{indice}")
    public ResponseEntity<Filme> buscarPorIndice(@PathVariable int indice){
        if (indice >= 0 && indice < listaFilmes.size()){
            return  ResponseEntity.status(200).body(listaFilmes.get(indice));
        }else {
            return ResponseEntity.status(204).build();
        }

    }


}
