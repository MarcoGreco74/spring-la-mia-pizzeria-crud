package it.lessons.spring.spring_la_mia_pizzeria_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lessons.spring.spring_la_mia_pizzeria_crud.model.Pizzeria;

public interface PizzeriaRepository extends JpaRepository<Pizzeria, Integer>{

    
}
