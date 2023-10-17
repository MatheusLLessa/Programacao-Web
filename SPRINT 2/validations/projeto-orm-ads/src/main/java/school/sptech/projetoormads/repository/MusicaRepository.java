package school.sptech.projetoormads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoormads.entity.Musica;

/*
  O Repositório (Repository) é quem de fato acessará o banco de dados.

  Na imensa maioria das vezes teremos 1 repositório para 1 entity;

  1 Repositório pode devolver um objeto diferente do especificado entre <>

  Sempre vamos estender JpaRepository, pois essa interface contempla os métodos mais utilizados
  quando o assunto é acesso a dados, exemplo:
  - findAll() para buscar todas as ocorrências da tabela;
  - findById() para buscar uma ocorrência ou registro em específico;
  - deleteById() para remover um registro através do id (PK);
  - save() que serve tanto para inserir quanto para atualizar, a lógica que ele faz é simples:
    - caso o objeto (Musica) tenha id definido, ou seja, id não for nulo, ele irá atualizar o registro em questão;
    - caso o objeto (Musica) não possua id definido, ele irá inserir na tabela;
*/
public interface MusicaRepository extends JpaRepository<Musica, Integer> {

}
