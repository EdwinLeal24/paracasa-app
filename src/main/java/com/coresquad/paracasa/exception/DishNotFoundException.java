package com.coresquad.paracasa.exception;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(Integer id) {
        super("Plato no encontrado " + id);
    }
}
