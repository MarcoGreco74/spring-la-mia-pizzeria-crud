package it.lessons.spring.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.lessons.spring.spring_la_mia_pizzeria_crud.model.Pizzeria;
import it.lessons.spring.spring_la_mia_pizzeria_crud.repository.PizzeriaRepository;

@Controller
@RequestMapping("/pizzeria")
public class PizzeriaController {

    @Autowired
    private PizzeriaRepository pizzeriaRepository;

    @GetMapping
    public String index(Model model) {
        List<Pizzeria> result = pizzeriaRepository.findAll();
        model.addAttribute("list", result);
        return "pizzeria/index";
    }
    
}
