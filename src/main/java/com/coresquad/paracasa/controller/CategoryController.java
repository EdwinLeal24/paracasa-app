package com.coresquad.paracasa.controller;

import com.coresquad.paracasa.entity.Category;
import com.coresquad.paracasa.exception.CategoryNotFoundException;
import com.coresquad.paracasa.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CategoryController {

    @Autowired
    private CategoryRepo categoryRepo;

    // Lista todas las categorias
    @GetMapping("/categories")
    public String getAllUsers(Model model) {
        model.addAttribute("categories", categoryRepo.findAll());
        return "categories";
    }

    // form para crear nueva categoria
    @GetMapping("/categories/new")
    public String createCategory (Model model) {

        Category category = new Category(); // Objeto que guardara los valores

        model.addAttribute("category", category);
        return "create_category";
    }

    // guarda una categoria
    @PostMapping("/categories")
    public String saveCategory (@ModelAttribute("category") Category category) {
        categoryRepo.save(category);
        return "redirect:/categories";
    }

    // form para editar una categoria
    @GetMapping(path = "/categories/edit/{id}")
    public String editCategoryForm(@PathVariable Integer id, Model model) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        model.addAttribute("category", category);
        return "edit_category";
    }

    //Guarda una categoria editada
    @PostMapping("/categories/{id}")
    public String updateCategory(@PathVariable Integer id, @ModelAttribute("category") Category category, Model model) {
        // Saca la categoria de la BD por su ID
        Category existentCategory = categoryRepo.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));

        // Actualiza
        existentCategory.setId(id);
        existentCategory.setCategoria(category.getCategoria());

        // Guarda
        categoryRepo.save(existentCategory);
        return "redirect:/categories";
    }

    //Elimina una categoria
    @GetMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        categoryRepo.deleteById(id);
        return "redirect:/categories";
    }


}
