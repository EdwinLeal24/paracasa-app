package com.coresquad.paracasa.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, targetEntity = Menu.class)
    @JoinTable(name = "pedido_menu", joinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_pedido", referencedColumnName = "id", nullable = false), foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private List<Menu> menus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
