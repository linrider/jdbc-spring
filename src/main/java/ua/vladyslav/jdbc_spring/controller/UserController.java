package ua.vladyslav.jdbc_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ua.vladyslav.jdbc_spring.service.impl.UserService;
import ua.vladyslav.jdbc_spring.util.UserValidator;
import ua.vladyslav.jdbc_spring.model.User;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "user/all";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user/user";
    }

    @GetMapping("/new")
    public String newUserPage(@ModelAttribute("user") User user) {
        return "user/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("user") @Valid User user,
            BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user/new";
        }
        userService.save(user);
        return "redirect:/user";
    }

    @GetMapping("/edit/{id}")
    public String editUserPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
            @ModelAttribute("user") @Valid User user,
            BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }
        userService.update(id, user);
        return "redirect:/user/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/user";
    }
}
