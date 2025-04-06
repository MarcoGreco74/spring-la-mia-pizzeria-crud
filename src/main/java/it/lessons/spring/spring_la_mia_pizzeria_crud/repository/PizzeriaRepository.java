package it.lessons.spring.spring_la_mia_pizzeria_crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lessons.spring.spring_la_mia_pizzeria_crud.model.Pizzeria;

public interface PizzeriaRepository extends JpaRepository<Pizzeria, Integer>{

    /*@Query(value = "SELECT * FROM pizzeria WHERE nome = :nome", nativeQuery = true)
        Optional<Pizzeria> trovaPerENome(@Param("nome") String nome);*/
        public List<Pizzeria> findByNomeContainingIgnoreCase(String nome);
        //@Query(value = "SELECT * FROM pizzeria WHERE nome like ?", nativeQuery = true)
       // public List<Pizzeria> findByNome(String nome);

}
