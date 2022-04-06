package com.coresquad.paracasa.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Integer id) {
        super("Categoria no encontrada " + id);
    }
}
