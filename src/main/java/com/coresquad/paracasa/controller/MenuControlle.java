package com.coresquad.paracasa.controller;

import com.coresquad.paracasa.entity.Menu;
import com.coresquad.paracasa.exception.MenuNotFoundException;
import com.coresquad.paracasa.repository.DishRepo;
import com.coresquad.paracasa.repository.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MenuControlle {

    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private DishRepo dishRepo;

    // Muestra los menus disponibles
    @GetMapping("/menus")
    public String getAllMenus(Model model) {
        model.addAttribute("menus", menuRepo.findAll());

        return "menu";
    }

    // Muestra el formulario de crear un menu
    @GetMapping("/menu/create")
    public String createMneu(Model model) {
        Menu menu = new Menu();

        model.addAttribute("menu", menu);
        model.addAttribute("dishes", dishRepo.findAll());

        return "create_menu";
    }

    // Guarda el menu creado
    @PostMapping("/menu")
    public String saveMenu(@ModelAttribute("menu") Menu menu) {
        menuRepo.save(menu);
        return "redirect:/menus";
    }

    // Muestra el form para editar un menun
    @GetMapping("/menu/edit/{id}")
    public String editMenu(@PathVariable Integer id, Model model) throws MenuNotFoundException {
        Menu menu = menuRepo.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
        model.addAttribute("menu", menu);
        model.addAttribute("platos", dishRepo.findAll());
        return "edit_menu";
    }

    // Guarda un menu editado
    @PostMapping("/menu/{id}")
    public String updateMenu(@PathVariable Integer id, @ModelAttribute("menu") Menu menu) throws MenuNotFoundException {
        Menu dbMenu = menuRepo.findById(id).orElseThrow(() -> new MenuNotFoundException(id));

        dbMenu.setId(id);
        dbMenu.setNombre(menu.getNombre());
        dbMenu.setDescripcion(menu.getDescripcion());
        dbMenu.setPrecio(menu.getPrecio());
        dbMenu.setPlatos(menu.getPlatos());

        menuRepo.save(dbMenu);
        return "redirect:/menus";
    }

    // Elimina un menu
    @GetMapping("/menu/{id}")
    public String removeMenu(@PathVariable Integer id) {
        menuRepo.deleteById(id);
        return "redirect:/menus";
    }
}
