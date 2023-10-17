package school.sptech.dynamicfinders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.dynamicfinders.entity.Filme;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {

  // SELECT * FROM Filme WHERE LOWER(nome) LIKE LOWER('%titulo%');
  // Retorna uma lista de filmes cujo título contém (ignorando maiúsculas/minúsculas) a string fornecida.
  List<Filme> findByNomeContainsIgnoreCase(String titulo);

  // SELECT * FROM Filme WHERE LOWER(diretor) LIKE LOWER('%diretor%');
  // Retorna uma lista de filmes cujo diretor contém (ignorando maiúsculas/minúsculas) a string do diretor fornecida.
  List<Filme> findByDiretorContainsIgnoreCase(String diretor);

  // SELECT * FROM Filme WHERE data_lancamento <= :periodo;
  // Retorna uma lista de filmes com data de lançamento menor ou igual à data fornecida.
  List<Filme> findByDataLancamentoLessThanEqual(LocalDate periodo);

  // SELECT * FROM Filme WHERE indicacao_oscar = true;
  // Retorna uma lista de filmes que foram indicados ao Oscar.
  List<Filme> findByIndicacaoOscarTrue();

  // SELECT COUNT(*) FROM Filme WHERE indicacao_oscar = false;
  // Conta a quantidade de filmes que não foram indicados ao Oscar.
  Long countByIndicacaoOscarFalse();

  // SELECT * FROM Filme ORDER BY custo_producao DESC LIMIT 1;
  // Retorna o primeiro filme encontrado ordenado pelo custo de produção em ordem decrescente.
  Optional<Filme> findFirstByOrderByCustoProducaoDesc();

  // SELECT * FROM Filme ORDER BY custo_producao DESC LIMIT 3;
  // Retorna uma lista dos top 3 filmes ordenados pelo custo de produção em ordem decrescente.
  List<Filme> findTop3ByOrderByCustoProducaoDesc();
}
