package school.sptech.aula02nivelamento;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/herois")
public class HeroiController {

    private List<Heroi> listaHerois = new ArrayList<>(List.of(
            new Heroi("Homem sereia", 8001, "Nadar", 1200, true),
            new Heroi("Batman", 9000, "Descer o k7", 40, true)
    ));
    @GetMapping
    public List<Heroi> todos(){
        return listaHerois;
    }

    @GetMapping("/favorito")
    public Heroi favorito(){
        return new Heroi("Homem sereia", 8001, "Nadar", 12000,true);
    }
}