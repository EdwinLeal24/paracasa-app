package com.coresquad.paracasa.controller;

import com.coresquad.paracasa.entity.Dish;
import com.coresquad.paracasa.repository.CategoryRepo;
import com.coresquad.paracasa.repository.DishRepo;
import com.coresquad.paracasa.repository.TypeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DishController {

    @Autowired
    private DishRepo dishRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private TypeRepo typeRepo;

    // Lista todos los platos
    @GetMapping("/dishes")
    public String getAllUsers(Model model) {
        model.addAttribute("dishes", dishRepo.findAll());
        model.addAttribute("types", typeRepo.findAll());

        return "dishes";
    }

    // form para crear nuevo plato
    @GetMapping("/dishes/new")
    public String createDish(Model model) {

        Dish dish = new Dish(); // Objeto que guardara los valores

        model.addAttribute("dish", dish);
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("types", typeRepo.findAll());
        return "create_dish";
    }

    // guarda un plato
    @PostMapping("/dishes")
    public String saveDish(@ModelAttribute("dish") Dish dish) {
        dishRepo.save(dish);
        return "redirect:/dishes";
    }

    // form para editar una categoria
    @GetMapping(path = "/dishes/edit/{id}")
    public String editDishForm(@PathVariable Integer id, Model model) {
        Dish dish = dishRepo.findById(id).orElseThrow(() -> new DishNotFoundException(id));
        model.addAttribute("dish", dish);
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("types", typeRepo.findAll());
        return "edit_dish";
    }

    // Guarda una categoria editada
    @PostMapping("/dishes/{id}")
    public String updateCategory(@PathVariable Integer id, @ModelAttribute("dish") Dish dish, Model model) {
        // Saca el plato de la BD por su ID
        Dish existentDish = dishRepo.findById(id).orElseThrow(() -> new DishNotFoundException(id));

        // Actualiza
        existentDish.setId(id);
        existentDish.setNombre(dish.getNombre());
        existentDish.setDescripcion(dish.getDescripcion());
        existentDish.setCalorias(dish.getCalorias());
        existentDish.setPrecio(dish.getPrecio());

        // Guarda
        dishRepo.save(existentDish);
        return "redirect:/dishes";
    }

    // Elimina un plato
    @GetMapping("/dishes/{id}")
    public String deleteDish(@PathVariable Integer id) {
        dishRepo.deleteById(id);
        return "redirect:/dishes";
    }

}
