package com.coresquad.paracasa.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/categories").setViewName("categories");
        registry.addViewController("/admin/dishes").setViewName("dishes");
        registry.addViewController("/admin/types").setViewName("types");
        registry.addViewController("/menus").setViewName("menu");
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
    }

}
