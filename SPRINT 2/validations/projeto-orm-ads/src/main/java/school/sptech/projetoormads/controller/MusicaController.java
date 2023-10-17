package school.sptech.projetoormads.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.projetoormads.entity.Musica;
import school.sptech.projetoormads.repository.MusicaRepository;

import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

  // Esta anotação indica que o Spring vai instanciar este objeto (implementar a interface que delcaramos) por nós
  @Autowired // Injeção de de dependencia
  private MusicaRepository musicaRepository;

  @GetMapping
  public ResponseEntity<List<Musica>> listar() {

    List<Musica> musicas = musicaRepository.findAll();

    if (musicas.isEmpty()) {
      return ResponseEntity.status(204).build();
    }

    return ResponseEntity.status(200).body(musicas);
  }

  /*
    @Valid previne o erro 500, e indica o que o corpo passado no endpoint deverá ser validado
    retornando o status 400 - Bad request
    Essa anotação deverá ser utilizada em todo lugar que recebemos um json (@RequestBody) e queremos validar o conteúdo desse objeto
  */
  @PostMapping
  public ResponseEntity<Musica> cadastrar(@RequestBody @Valid Musica novaMusica) {
    Musica musicaCadastrada = this.musicaRepository.save(novaMusica);
    return ResponseEntity.status(201).body(musicaCadastrada);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Musica> buscarPorId(@PathVariable Integer id) {
    //O método 'of' espera receber um Optional, se ele estiver vazio, retorna automaticamente 404,
    // caso não esteja vazio, retorna 200 com o objeto no corpo da resposta.
    return ResponseEntity.of(this.musicaRepository.findById(id));

// Exemplo mais verboso:
//    Optional<Musica> optionalMusica = this.musicaRepository.findById(id);
//    if (optionalMusica.isPresent()){
//      Musica musica = optionalMusica.get();
//      return ResponseEntity.status(200).body(musica);
//    }
//
//    return ResponseEntity.status(404).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Musica> atualizar(
          @PathVariable Integer id,
          @RequestBody @Valid Musica musica
  ) {

    //Essa linha garante o que o objeto a ser atualizado é o id especificado na URI
    musica.setId(id);

    // 'existsById()' como o nome sugere, verifica se há algum registro com o id especificado e retorna um boolean;
    if (this.musicaRepository.existsById(id)) {

      // Nesse caso, por ter um id especificado no objeto, o método save() irá atualizar o registro em questão
      Musica musicaAtualizada = this.musicaRepository.save(musica);
      return ResponseEntity.status(200).body(musicaAtualizada);
    }

    return ResponseEntity.status(404).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remover(@PathVariable Integer id) {

    /*
    // Outra maneira caso fosse necessário devolver o objeto recém excluído na resposta:
    Optional<Musica> musicaOptional = this.musicaRepository.findById(id);

    if (musicaOptional.isPresent()) {
      this.musicaRepository.deleteById(id);
      return ResponseEntity.status(200).body(musicaOptional.get());
    }

    return ResponseEntity.status(404).build();
    */

    if (this.musicaRepository.existsById(id)) {
      // O deleteById() permite excluir um registro dado uma pk (primary key))
      // Seguindo a lógica de atualização, sempre verifique se existe ants de tentar remover;
      this.musicaRepository.deleteById(id);
      return ResponseEntity.status(200).build();
    }

    return ResponseEntity.status(404).build();
  }
}
