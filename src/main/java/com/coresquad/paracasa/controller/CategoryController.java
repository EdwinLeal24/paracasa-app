package com.coresquad.paracasa.controller;

import com.coresquad.paracasa.entity.Category;
import com.coresquad.paracasa.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="/category")
public class CategoryController {

    @Autowired
    private CategoryRepo categoryRepo;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewCategory (@RequestParam String category) {
        Category categoryDB = new Category();
        categoryDB.setCategoria(category);
        categoryRepo.save(categoryDB);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Category> getAllUsers() {
        return categoryRepo.findAll();
    }
}
