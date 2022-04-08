package com.coresquad.paracasa.exception;

public class MenuNotFoundException extends Exception{
    public MenuNotFoundException(Integer id) {
        super("Menu con id: " + id + " no se ha encontrado!");
    }
}
