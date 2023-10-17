package sptech.school.livraria.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sptech.school.livraria.Enitity.Livro;

import java.time.LocalDate;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    @Modifying
    @Query("UPDATE Livro l SET l.titulo = :novoTitulo, l.autor = :novoAutor WHERE l.id = :livroId")
    void atualizarTituloEautor(@Param("livroId") String livroId, @Param("novoTitulo") String novoTitulo, @Param("novoAutor") Long novoAutor);

    @Modifying
    @Transactional
    @Query("DELETE FROM Livro l WHERE l.id = :id")
    Livro deletarPorId(Long id);

    @Modifying
    @Query("SELECT l FROM Livro l WHERE l.dataPublicacao > :dataInformada")
    List<Livro> buscarLivrosNaoPublicados(LocalDate dataInfomada);


    List<Livro> findById(Long id);

    List<Livro> findByTop5ByDataPublicacaoBeforeOrderByDataPublicacaoDesc();

    List<Livro> findFirstByOrderByPrecoDesc();

    boolean existsById(Long id);
}
