package com.coresquad.paracasa.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class Home {
    public String index() {
        return "index";
    }
}
