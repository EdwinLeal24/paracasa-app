package com.coresquad.paracasa.controller;

import com.coresquad.paracasa.entity.Pedido;
import com.coresquad.paracasa.entity.User;
import com.coresquad.paracasa.repository.MenuRepo;
import com.coresquad.paracasa.repository.PedidoRepo;
import com.coresquad.paracasa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class PedidoController {

    @Autowired
    private PedidoRepo pedidoRepo;

    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private UserRepo userRepo;

    // Muestra el formulario de crear un pedido
    @GetMapping("/user/pedido/create")
    public String createPedido(Model model) {
        Pedido pedido = new Pedido();

        model.addAttribute("menus", menuRepo.findAll());
        model.addAttribute("pedido", pedido);

        return "create_pedido";
    }

    // Guarda el pedido creado
    @PostMapping("/user/pedido/save/")
    public String savePedido(@ModelAttribute("pedido") Pedido pedido, Principal principal) {
        String username = principal.getName();
        Integer userId = userRepo.getUserByUsername(username);
        User user = userRepo.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " no encontrado"));
        pedido.setUser(user);
        pedidoRepo.save(pedido);
        return "redirect:/user/pedido/create?success";
    }


}
