package com.coresquad.paracasa.controller;

import com.coresquad.paracasa.entity.Type;
import com.coresquad.paracasa.exception.TypeNotFoundException;
import com.coresquad.paracasa.repository.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TypeController {

    @Autowired
    private TypeRepo typeRepo;

    @GetMapping("/types")
    public String getAllTypes(Model model) {
        model.addAttribute("types", typeRepo.findAll());
        return "types";
    }

    @GetMapping("/type/add")
    public String createType(Model model) {
        Type type = new Type();
        model.addAttribute("type", type);
        return "add_type";
    }

    @PostMapping("/types")
    public String saveType(@ModelAttribute("type") Type type) {
        typeRepo.save(type);
        return "redirect:/types";
    }

    @GetMapping("/types/edit/{id}")
    public String editType(@PathVariable Integer id, Model model) throws TypeNotFoundException {
        Type type = typeRepo.findById(id).orElseThrow(() -> new TypeNotFoundException(id));
        model.addAttribute("type", type);
        return "edit_type";
    }


    @PostMapping("/types/{id}")
    public String updateType(@PathVariable Integer id, @ModelAttribute("type") Type type) throws TypeNotFoundException {
        Type dbType = typeRepo.findById(id).orElseThrow(() -> new TypeNotFoundException(id));

        dbType.setId(id);
        dbType.setTipo(type.getTipo());

        typeRepo.save(dbType);
        return "redirect:/types";
    }

    @GetMapping("/types/{id}")
    public String removeType(@PathVariable Integer id) {
        typeRepo.deleteById(id);
        return "redirect:/types";
    }
}
