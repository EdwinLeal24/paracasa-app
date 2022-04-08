package com.coresquad.paracasa.controller;

public class DishNotFoundException extends RuntimeException {
  public DishNotFoundException(Integer id) {
    super("Plato no encontrado " + id);
  }
}