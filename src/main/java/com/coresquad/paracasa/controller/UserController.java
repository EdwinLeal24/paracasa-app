package com.coresquad.paracasa.controller;

import com.coresquad.paracasa.entity.Pedido;
import com.coresquad.paracasa.entity.User;
import com.coresquad.paracasa.enums.Rol;
import com.coresquad.paracasa.exception.PedidoNotFoundException;
import com.coresquad.paracasa.repository.PedidoRepo;
import com.coresquad.paracasa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PedidoRepo pedidoRepo;

    // Muestra el formulario de registro
    @GetMapping("/register")
    public String registerUser(Model model) {
        User user = new User();

        model.addAttribute("user", user);

        return "register";
    }

    // Guarda el usuario creado
    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoll(Rol.USER);
        userRepo.save(user);
        return "redirect:/register?success";
    }

    // Muestra el form para editar un user
    @GetMapping("/profile")
    public String editMenu(Model model, Principal principal) {
        String username = principal.getName();
        Integer id = userRepo.getUserByUsername(username);
        User user = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Usuraio no encontrado"));
        model.addAttribute("user", user);

        List<Integer> pedidos_id = pedidoRepo.getPedidoByUserId(id);
        List<Pedido> pedidos = new ArrayList<Pedido>();

        for (Integer id_pedido : pedidos_id) {
            Pedido pedido = pedidoRepo.findById(id_pedido).orElseThrow(() -> new PedidoNotFoundException(id_pedido));
            if (pedido.getUser().getId() == id) {
                pedidos.add(pedido);
            }
        }
        model.addAttribute("pedidos", pedidos);
        return "edit_user";
    }

    // Guarda un user editado
    @PostMapping("/profile")
    public String updateMenu(@ModelAttribute("user") User user, Principal principal) {
        String username = principal.getName();
        Integer id = userRepo.getUserByUsername(username);
        User dbUser = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Usuraio no encontrado"));

        dbUser.setId(id);
        dbUser.setNombre(user.getNombre());
        dbUser.setCorreo(user.getCorreo());
        dbUser.setRoll(user.getRoll());

        userRepo.save(dbUser);
        return "redirect:/menus";
    }
}
