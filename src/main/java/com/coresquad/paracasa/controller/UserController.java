package com.coresquad.paracasa.controller;

import com.coresquad.paracasa.entity.User;
import com.coresquad.paracasa.enums.Rol;
import com.coresquad.paracasa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserRepo userRepo;

    // Muestra el formulario de registro
    @GetMapping("/register")
    public String registerUser(Model model) {
        User user = new User();

        model.addAttribute("user", user);

        return "register";
    }

    // Guarda el menu creado
    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoll(Rol.USER);
        userRepo.save(user);
        return "redirect:/register?success";
    }
}
