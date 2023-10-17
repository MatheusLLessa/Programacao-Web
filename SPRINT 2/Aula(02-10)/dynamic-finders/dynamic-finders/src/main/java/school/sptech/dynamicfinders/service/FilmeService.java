package school.sptech.dynamicfinders.service;

/*
    @Component
    @Controller
    @RestController
    @Service
    @Repository
    @Configuration

*/

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.dynamicfinders.entity.Filme;
import school.sptech.dynamicfinders.exception.EntidadeNaoEncontradaException;
import school.sptech.dynamicfinders.repository.FilmeRepository;

import java.util.List;

@Service
public class FilmeService {
    private final FilmeRepository repository;

    public FilmeService(FilmeRepository filmeRepository){
        this.repository = filmeRepository;
    }

    public Filme salvar(Filme filme){
       Filme filmeSalvo = this.repository.save(filme);
        return filmeSalvo;
    }

    public List<Filme> listar(){
        List<Filme> filmes = this.repository.findAll();
        return filmes;
    }

    public Filme buscarPorId(int id){
        Filme filme = this.repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Filme não encontrado"));
        return filme;
    }

    public Filme atualizar(int id, Filme filmeAtualizado){
        if(this.repository.existsById(id)){
            filmeAtualizado.setId(id);
            Filme filmeSalvo = this.repository.save(filmeAtualizado);
            return filmeSalvo;
        }
        throw new EntidadeNaoEncontradaException("Filme não encontrado");
    }
}
