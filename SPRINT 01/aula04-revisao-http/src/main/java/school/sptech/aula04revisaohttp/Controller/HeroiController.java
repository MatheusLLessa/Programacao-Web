package school.sptech.aula04revisaohttp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.aula04revisaohttp.Entity.Heroi;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/herois")
public class HeroiController {

  private List<Heroi> herois = new ArrayList<>();

  @GetMapping
  public List<Heroi> listar() {
    return herois;
  }

  @GetMapping("/{indice}")
  public ResponseEntity<Heroi> existeHeroi(@PathVariable int indice) {
    if (existeIndice(indice).equals(true)) {
      return ResponseEntity.status(200).body(herois.get(indice));
    }
    return ResponseEntity.status(400).build();
  }

  @PostMapping
  public ResponseEntity<Heroi> adicionarHeroi(@RequestBody Heroi heroi) {
    if (heroi.getNome().isBlank() || heroi.getNome().length() < 3|| heroi.getHabilidade().isBlank() || heroi.getHabilidade().length() < 3 || heroi.getIdade() < 0 || heroi.getForca() < 0 || heroi.getForca() > 100) {
      return ResponseEntity.status(400).build();
    }
    herois.add(heroi);
    return ResponseEntity.status(200).body(heroi);
  }

  @PutMapping("/{indice}")
  public ResponseEntity<Heroi> atualizarHeroi(@RequestBody Heroi heroi, @PathVariable int indice) {
    if (existeIndice(indice)) {

      if (heroi.getNome().isBlank() || heroi.getNome().length() < 3 || heroi.getHabilidade().isBlank() || heroi.getHabilidade().length() < 3 || heroi.getIdade() < 0 || heroi.getForca() < 0 || heroi.getForca() > 100) {
        return ResponseEntity.status(400).build();
      }
    }
    System.out.println("TO NO IF");
    herois.set(indice, heroi);

    return ResponseEntity.status(200).body(heroi);
  }

  @DeleteMapping("/{indice}")
  public ResponseEntity<Void> deletarHeroi(@PathVariable int indice){
    if (existeIndice(indice).equals(true)){
      herois.remove(indice);
      return ResponseEntity.status(204).build();
    }
    return ResponseEntity.status(400).build();
  }

  private Boolean existeIndice(int indice){
    if (indice<0||indice>herois.size()){
      return false;
    }
    return true;
  }
}
