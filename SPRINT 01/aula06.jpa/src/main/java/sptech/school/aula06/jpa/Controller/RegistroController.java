package sptech.school.aula06.jpa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.aula06.jpa.Enitity.RegistroFinanceiro;
import sptech.school.aula06.jpa.repositories.RegistroFinanceiroRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registros")
public class RegistroController {
    @Autowired
    private RegistroFinanceiroRepository repository;

    private List<RegistroFinanceiro> listaRegistro = new ArrayList<>();

    // Cadastrar
    @PostMapping()
    public ResponseEntity<RegistroFinanceiro> cadastrar(@RequestBody RegistroFinanceiro registro) {
        if (validar(registro)) {
            return ResponseEntity.status(400).build();
        }
        RegistroFinanceiro registroSalvo = this.repository.save(registro);
        return ResponseEntity.status(201).body(registroSalvo);
    }

    // Listar os usuários
    @GetMapping()
    public ResponseEntity<List<RegistroFinanceiro>> listar() {
        List<RegistroFinanceiro> registros = this.repository.findAll();

        if (registros.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(registros);

        /*
        List<RegistroFinanceiro> registros = this.repository.findAll();
        if (listaRegistro.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).build();

         */
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroFinanceiro> listarIndice(@PathVariable int id) {
        Optional<RegistroFinanceiro> registroOpt = this.repository.findById(id);

        if(registroOpt.isPresent()){
            RegistroFinanceiro registroFinanceiro = registroOpt.get();
            return ResponseEntity.status(200).body(registroFinanceiro);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroFinanceiro> atualizarUsuarioIndice(@PathVariable int id, @RequestBody RegistroFinanceiro registro) {
        registro.setId(id);
        // Save:
        // Se objeto não possui ID -> ele cria um registro
        // Se objeto possui ID -> ele atualiza

        if (this.repository.existsById(id)) {
            RegistroFinanceiro registroAtualizado = this.repository.save(registro);
            return ResponseEntity.status(200).body(registroAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RegistroFinanceiro> deletar(@PathVariable int id) {
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/ganhos")
    public ResponseEntity<List<RegistroFinanceiro>> getGanhos() {
        List<RegistroFinanceiro> registroI = this.repository.findAll();

        List<RegistroFinanceiro> ganhos = registroI.stream().filter(listaRegistro -> listaRegistro.getValor() > 0).toList();

        if ((ganhos.isEmpty())) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(ganhos);
        }
    }

    @GetMapping("/despesas")
    public ResponseEntity<List<RegistroFinanceiro>> getDespesas() {
        List<RegistroFinanceiro> ganhos = this.listaRegistro.stream().filter(registroFinanceiro -> registroFinanceiro.getValor() < 0).toList();

        if ((ganhos.isEmpty())) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(ganhos);
        }
    }


    public Boolean validarindice(int indice) {
        return indice >= 0 && indice < listaRegistro.size();
    }

    private boolean validar(RegistroFinanceiro registro) {
        if (registro.getValor() == 0) {
            return true;
        }
        return false;
    }
}
