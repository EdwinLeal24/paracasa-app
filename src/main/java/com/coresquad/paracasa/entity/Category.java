package com.coresquad.paracasa.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String categoria;

    @ManyToMany(mappedBy = "categorias")
    private List<Dish> platos;

    public Integer getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Dish> getPlatos() {
        return platos;
    }

    public void setPlatos(List<Dish> platos) {
        this.platos = platos;
    }
}
