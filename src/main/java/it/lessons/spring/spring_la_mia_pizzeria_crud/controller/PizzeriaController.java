package it.lessons.spring.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.lessons.spring.spring_la_mia_pizzeria_crud.model.Pizzeria;
import it.lessons.spring.spring_la_mia_pizzeria_crud.repository.PizzeriaRepository;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/pizzeria")
public class PizzeriaController {

    @Autowired
    private PizzeriaRepository pizzeriaRepository;
    
    @GetMapping
    public String index(Model model, @RequestParam(value="keyword", required=false) String nome){
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

   @GetMapping("/showdescrizione/{descrizione}")
      public String showdescrizione(@PathVariable("descrizione") String descrizione, Model model){
        List<Pizzeria> resultDescrizione = pizzeriaRepository.findDescrizioneContainingIgnoreCase(descrizione);
        model.addAttribute("pizza", resultDescrizione);
        return "pizzeria/showdescrizione";
   }

   @GetMapping("/create")
      public String createPizza(Model model){
        model.addAttribute("nuovapizza", new Pizzeria());
        return "pizzeria/create";
    }
    
   @PostMapping("/create")
   public String storePizza(@Valid @ModelAttribute("nuovapizza") Pizzeria formPizza, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
    if(bindingResult.hasErrors()) {
        return "pizzeria/create";
        }
        pizzeriaRepository.save(formPizza);
        redirectAttributes.addFlashAttribute("successMessage", "Pizza creata con successo!");
        return "redirect:/pizzeria";
   }

   @GetMapping("/edit/{id}")
   public String edit(@PathVariable("id") Integer id, Model model){
    model.addAttribute("modificapizza", pizzeriaRepository.findById(id).get());
    return "/pizzeria/edit";
   }

   @PostMapping("edit/{id}")
   public String update(@Valid @ModelAttribute("modificapizza") Pizzeria formPizza, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
    if(bindingResult.hasErrors()) {
        return "pizzeria/edit";
        }
        pizzeriaRepository.save(formPizza);
        redirectAttributes.addFlashAttribute("successMessage", "Pizza modificata con successo!");
        return "redirect:/pizzeria";
   }

   @PostMapping("delete/{id}")
   public String delete(@PathVariable("id") Integer id){
    pizzeriaRepository.deleteById(id);
    return "redirect:/pizzeria";
   }
}
