package com.coresquad.paracasa.controller;

import com.coresquad.paracasa.entity.Category;
import com.coresquad.paracasa.exception.CategoryNotFoundException;
import com.coresquad.paracasa.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
//@RequestMapping(path="/category")
public class CategoryController {

    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping("/categories")
    public String getAllUsers(Model model) {
        model.addAttribute("categories", categoryRepo.findAll());
        return "categories";
    }

    // TODO - Edwin: Modificar los endpoints para que devuelvan una plantilla como "getAllUsers"
    @PostMapping(path="/add")
    public @ResponseBody
    String addNewCategory (@RequestBody String category) {
        Category categoryDB = new Category();
        categoryDB.setCategoria(category);
        categoryRepo.save(categoryDB);
        return "Saved";
    }

    @GetMapping(path = "/categories/{id}")
    public @ResponseBody Category getOneCategory(@PathVariable Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        return category;
    }

    @PutMapping("/categories/{id}")
    public @ResponseBody Category updateCategory(@RequestBody Category newCategory, @PathVariable Integer id) {

        return categoryRepo.findById(id)
                .map(category -> {
                    category.setCategoria(newCategory.getCategoria());
                    category.setPlatos(newCategory.getPlatos());
                    return categoryRepo.save(category);
                })
                .orElseGet(() -> {
                    newCategory.setId(id);
                    return categoryRepo.save(newCategory);
                });
    }

    @DeleteMapping("/categories/{id}")
    public @ResponseBody String
    deleteEmployee(@PathVariable Integer id) {
        categoryRepo.deleteById(id);
        return "Categoria " + id + " eliminada correctamente!";
    }


}
