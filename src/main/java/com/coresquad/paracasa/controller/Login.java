package com.coresquad.paracasa.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
public class Login {
    public String login() {
        return "login";
    }
}
