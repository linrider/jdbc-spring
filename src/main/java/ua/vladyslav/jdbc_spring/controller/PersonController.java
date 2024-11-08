package ua.vladyslav.jdbc_spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.vladyslav.jdbc_spring.model.Person;
import ua.vladyslav.jdbc_spring.service.PersonService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String savePerson(
            @RequestParam("name") String name,
            @RequestParam("age") int age,
            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);

        Person person = new Person(0, name, age);

        personService.save(person);
        return "saved";
    }

    @GetMapping("/sign-up")
    public String getSignUpPage() {
        return "sign-up";
    }

    @GetMapping("/all")
    public String getAllPersons(Model model) {
        List<Person> persons = personService.getAll();
        model.addAttribute("persons", persons);
        return "show-all";
    }

    @GetMapping("/person/{id}")
    public String getPersonById(@PathVariable("id") int id, Model model) {
        Person person = personService.getById(id);
        model.addAttribute("id", id);
        model.addAttribute("name", person.getName());
        model.addAttribute("age", person.getAge());
        return "person-info";
    }

    @GetMapping("/delete/{id}")
    public String deletePersonById(@PathVariable("id") int id) {
        personService.deleteById(id);
        return "person-deleted";
    }

    @GetMapping("/update-page")
    public String getUpdatePage() {
        return "update-page";
    }

    @GetMapping("/update")
    public String updatePerson(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("age") int age,
            Model model) {

        Person person = new Person(id, name, age);
        personService.update(id, person);
                model.addAttribute("id", id);
                model.addAttribute("name", name);
                model.addAttribute("age", age);
        return "updated";
    }

}
