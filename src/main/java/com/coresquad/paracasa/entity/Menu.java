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

    private String descripcion;
    private Float precio;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, targetEntity = Dish.class)
    @JoinTable(name = "menu_dish", joinColumns = @JoinColumn(name = "id_plato", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "id", nullable = false), foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private List<Dish> platos;

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
}
