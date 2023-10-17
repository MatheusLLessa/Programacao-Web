package sptech.school.exerciciofilme.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.exerciciofilme.Enitity.Filme;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {
    //Like %s%, ignorando Case

    List<Filme> findByNomeContainingIgnoreCase(String nome);
    List<Filme> findByDiretorContains(String diretor);

    List<Filme> findByDataLancamentoLessThanEqual (LocalDate date);

    List<Filme> findByIndicacaoOscarTrue();

    List<Filme> findByIndicacaoOscarFalse();

    Optional<Filme> findFirstByOrderByCustoProducaoDesc();

    List<Filme> findTop3ByOrderByCustoProducaoDesc();


 }
