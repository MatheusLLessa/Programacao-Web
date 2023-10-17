package school.sptech.praticacomlista;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {
    private List<String> listaPokemon = new ArrayList<>();
    @GetMapping
    public String contagem(){
        return String.format("Total de pokemon cadastrados %s:", listaPokemon.size());
    }

    @GetMapping("/cadastrar/{pokemon}")
    public String pokemonCadastrado(@PathVariable String pokemon){
        listaPokemon.add(pokemon);
        return "Pokemon cadastrado";
    }

    @GetMapping("/recuperar/{indice}")
    public String recuperarPokemon(@PathVariable int indice){
        return verificarIndiceValido(indice) ? indice : verificarIndiceValido();
    }

    public boolean verificarIndiceValido(int indice){
        if (listaPokemon.size() - 1 >= indice && indice >= 0){
            return true;
        }
        return false;
    }
}
