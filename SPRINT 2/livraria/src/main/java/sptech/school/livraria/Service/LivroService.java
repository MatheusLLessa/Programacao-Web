package sptech.school.livraria.Service;

import sptech.school.livraria.Enitity.Livro;
import sptech.school.livraria.Exception.EntidadeNaoEncontradaException;
import sptech.school.livraria.Repository.LivroRepository;

import java.time.LocalDate;
import java.util.List;

public class LivroService {
    private LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public Livro salvar(Livro livro){
        Livro livroSalvo = this.repository.save(livro);
        return livroSalvo;
    }

    public List<Livro> listar(){
        List<Livro> livros = this.repository.findAll();
        return livros;
    }


    public Livro buscarPorId(Long id){
        Livro livro = this.repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Livro não encontrado")
        );
        return livro;
    }

    public Livro atualizarPorId(Long id, Livro livroAtualizado){
        if (this.repository.existsById(id)){
            livroAtualizado.setId((long) id);
            Livro livroSalvo = this.repository.save(livroAtualizado);
            return livroAtualizado;
        }
        throw new EntidadeNaoEncontradaException("Livro não encontrado");
    }

    public Livro deletarPorId(Long id){
        Livro livro = this.buscarPorId(id);
        repository.deletarPorId(id);
        return livro;
    }

    public void atualizarPorId(Long id, String titulo, String autor) {
        this.buscarPorId(id);
        this.repository.atualizarTituloEautor(titulo, autor, id);
    }

    public List<Livro> buscarNaoPublicado(LocalDate data){
        List<Livro> livrosEncontrados = this.repository.buscarLivrosNaoPublicados(data);
        return livrosEncontrados;
    }

    public List<Livro> buscarOs5AteDataInformada(LocalDate dataInformada) {
        List<Livro> top5 = this.repository.findByTop5ByDataPublicacaoBeforeOrderByDataPublicacaoDesc();
        return top5;
    }

    public Livro maiorCusto() {
        Livro qtdNaoIndicados =
                this.repository.findFirstByOrderByPrecoDesc().orElseThrow(
                        () -> new EntidadeNaoEncontradaException("Livro de maior custo não encontrado")
                );
        return qtdNaoIndicados;
    }
}
