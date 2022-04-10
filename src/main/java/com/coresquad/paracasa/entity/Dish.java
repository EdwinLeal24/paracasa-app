
package com.coresquad.paracasa.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;
    private Float precio;
    private String calorias;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, targetEntity = Category.class)
    @JoinTable(name = "dish_categories", joinColumns = @JoinColumn(name = "id_categoria", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_plato", referencedColumnName = "id", nullable = false), foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private List<Category> categorias;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, targetEntity = Menu.class)
    @JoinTable(name = "menu_dish", joinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_plato", referencedColumnName = "id", nullable = false), foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private List<Menu> menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo")
    private Type tipo;

    // GETTERS & SETTERS

    public List<Category> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Category> categorias) {
        this.categorias = categorias;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }

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

    public String getCalorias() {
        return calorias;
    }

    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }
}
