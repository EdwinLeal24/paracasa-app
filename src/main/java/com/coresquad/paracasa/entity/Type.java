package com.coresquad.paracasa.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String tipo; //Entrante, principal, postre, etc..

    @OneToMany(mappedBy = "tipo")
    private List<Dish> platos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
