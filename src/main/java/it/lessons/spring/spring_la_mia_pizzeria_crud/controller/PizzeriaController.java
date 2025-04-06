package it.lessons.spring.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.lessons.spring.spring_la_mia_pizzeria_crud.model.Pizzeria;
import it.lessons.spring.spring_la_mia_pizzeria_crud.repository.PizzeriaRepository;


@Controller
@RequestMapping("/pizzeria")
public class PizzeriaController {

    @Autowired
    private PizzeriaRepository pizzeriaRepository;

    /*@GetMapping
    public String index(Model model) {
        List<Pizzeria> result = pizzeriaRepository.findAll();
        model.addAttribute("list", result);
        return "pizzeria/index";
    }*/

    @GetMapping
    public String index(Model model, @RequestParam(name="keyword", required=false) String nome){
        List<Pizzeria> result;
        if(nome != null  && !nome.isBlank()){
            //result = pizzeriaRepository.findByNome(nome);
            result = pizzeriaRepository.findByNomeContainingIgnoreCase(nome);
        }else{
            result = pizzeriaRepository.findAll();
        }
        model.addAttribute("list", result);
        return "pizzeria/index";
    }

    @GetMapping("/show_id/{id}")
    public String show_id(@PathVariable("id") Integer id, Model model) {
        Optional<Pizzeria> optPizzeria = pizzeriaRepository.findById(id);
        if(optPizzeria.isPresent()){
            model.addAttribute("pizza", optPizzeria.get());
            return "pizzeria/show_id";
        }
        model.addAttribute("errorMsg", "Pagina non trovata");
        return "pizzeria/error_page";
    }

    /*@GetMapping("/show_name")
   public String showname(Model model, @RequestParam(name="keyword", required=false) String nome) {
      List<Pizzeria> result;
      if(nome != null && !nome.isBlank()) {
         result = pizzeriaRepository.findByNome(nome);
         //result = pizzeriaRepository.findByNomeContainingIgnoreCase(nome);
         //model.addAttribute("list", result);
      } else {
         //result = pizzeriaRepository.findAll();
           model.addAttribute("errorMsg", "Pagina non trovata");
           return "pizzeria/error_page";
      }
        model.addAttribute("list", result);
        return "pizzeria/showname";
   }*/	
  //// Problemi ////
   // come creare una nuova route, diversa da index che riceve i dati del form ? 
   // perchè la query custom findByNome non funziona ?
}
