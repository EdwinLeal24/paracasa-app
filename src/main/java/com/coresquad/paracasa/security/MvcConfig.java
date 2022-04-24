package com.coresquad.paracasa.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/menu").setViewName("menu");
        registry.addViewController("/dishes").setViewName("dishes");
        registry.addViewController("/categories").setViewName("categories");
        registry.addViewController("/types").setViewName("types");
        registry.addViewController("/login").setViewName("login");
    }

}
