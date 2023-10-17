package school.sptech.projeto03.Controller;

import org.springframework.web.bind.annotation.*;
import school.sptech.projeto03.Entity.Musica;

import java.util.ArrayList;
import java.util.List;

//@Controller // MVC -> retorna paginas em html
@RestController // API em RESET
@RequestMapping("/musicas")
public class MusicaController {
    private List<Musica> musicas = new ArrayList<>();

    //@GetMapping("/cadastrar/{nome}/{artistar}")
    @PostMapping
    public Musica cadastrar(@RequestBody Musica novaMusica){

        //Musica novaMusica = new Musica(nome,artistar);

        this.musicas.add(novaMusica);
        return novaMusica;
    }

    @PutMapping
    public Musica atualizar(@PathVariable int indice,
                            @RequestBody Musica musicaAtualizada){
        if(isIndiceValido()){
            return
        }
        return null;
    }

    @GetMapping // usar os verbos da lingua portuguesa
    public List<Musica> listar(){
        return this.musicas;
    }

    @GetMapping("/{indice}")
    public Musica consultarPorIndice(@PathVariable int indice){
        if(indice >= 0 && indice < this.musicas.size()){
            return this.musicas.get(indice);
        }
        return null;
    }


    private boolean isIndiceValido(int indice){
        return indice >= 0 && indice < this.musicas.size();
    }

}

