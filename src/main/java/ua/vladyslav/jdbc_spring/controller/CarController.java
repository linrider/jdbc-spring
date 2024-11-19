package ua.vladyslav.jdbc_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import ua.vladyslav.jdbc_spring.model.Car;
import ua.vladyslav.jdbc_spring.service.impl.CarService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("cars", carService.getAll());
        return "car/all";
    }

    @GetMapping("/{id}")
    public String getCar(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("car", carService.getById(id));
        return "car/car";
    }

   @GetMapping("/new")
   public String newCarPage(@ModelAttribute("car") Car car) {
       return "car/new";
   }

   @PostMapping()
   public String save(@ModelAttribute("car") Car car) {
        carService.save(car);
       return "redirect:/car";
   }

   @GetMapping("edit/{id}")
   public String editCarPage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("car", carService.getById(id));
       return "car/edit";
   }

   @PatchMapping("/{id}")
   public String update(@PathVariable("id") Integer id, @ModelAttribute("car") Car car) {
    carService.update(id, car);
    return "redirect:/car/" + id;
   }

   @DeleteMapping("/{id}")
   public String delete(@PathVariable("id") Integer id) {
        carService.deleteById(id);
        return "redirect:/car";
   }
}
