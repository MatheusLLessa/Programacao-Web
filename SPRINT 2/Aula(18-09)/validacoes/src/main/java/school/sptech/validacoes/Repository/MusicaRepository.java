package school.sptech.validacoes.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.validacoes.Enitity.Musica;

// É uma camada que vai validar com o BD
public interface MusicaRepository extends JpaRepository<Musica, Integer> {
}
