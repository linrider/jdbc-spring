package ua.vladyslav.jdbc_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

        Person person = new Person();
        person.setName(name);
        person.setAge(age);

        personService.save(person);
        return "result";
    }

    @GetMapping("/sign-up")
    public String getSignUpPage() {
        return "sign-up";
    }
}
