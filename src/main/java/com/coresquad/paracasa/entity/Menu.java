package com.coresquad.paracasa.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public List<Dish> getPlatos() {
        return platos;
    }

    public void setPlatos(List<Dish> platos) {
        this.platos = platos;
    }

    private String descripcion;
    private Float precio;

    @ManyToMany(cascade =  CascadeType.ALL)
    @JoinTable(
            name = "menu_dish",
            joinColumns = @JoinColumn(name = "id_plato", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "id", nullable = false)
    )
    private List<Dish> platos;
}
