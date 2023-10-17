package sptech.school.aula06.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.school.aula06.jpa.Enitity.RegistroFinanceiro;

@Repository
public interface RegistroFinanceiroRepository extends JpaRepository<RegistroFinanceiro, Integer> {

}
