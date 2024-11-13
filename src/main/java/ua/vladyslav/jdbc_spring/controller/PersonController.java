package ua.vladyslav.jdbc_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.vladyslav.jdbc_spring.model.Person;
import ua.vladyslav.jdbc_spring.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("people", personService.getAll());
        return "person/all";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getById(id));
        return "person/person";
    }
    
    @GetMapping("/new")
    public String newPersonPage(@ModelAttribute("person") Person person) {
        return "person/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("person") Person person) {
        personService.save(person);
        return "redirect:/person";
    }
    
    @GetMapping("/edit/{id}")
    public String editPersonPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getById(id));
        return "person/edit";
    }
    
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        personService.update(id, person);
        return "redirect:/person/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.deleteById(id);
        return "redirect:/person";
    }
}
