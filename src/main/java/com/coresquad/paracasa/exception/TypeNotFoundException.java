package com.coresquad.paracasa.exception;

public class TypeNotFoundException extends Exception{
    public TypeNotFoundException(Integer id) {
        super("El tipo con id " + id + " no se ha encontrado");
    }
}
