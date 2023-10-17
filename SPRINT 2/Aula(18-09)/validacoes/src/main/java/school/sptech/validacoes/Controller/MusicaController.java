package school.sptech.validacoes.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.validacoes.Enitity.Musica;
import school.sptech.validacoes.Repository.MusicaRepository;

import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {
    @Autowired
    private MusicaRepository repository;

    @GetMapping
    public ResponseEntity<List<Musica>> lista(){
        List<Musica> musicas = this.repository.findAll();

        if(musicas.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(musicas);
    }

    @PostMapping
    public ResponseEntity<Musica> cadastrar(@RequestBody @Valid Musica musica){
        Musica musicaSalva = this.repository.save(musica);
        return ResponseEntity.status(201).body(musicaSalva);
    }

}
