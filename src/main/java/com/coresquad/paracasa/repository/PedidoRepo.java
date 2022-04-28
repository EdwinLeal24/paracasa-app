package com.coresquad.paracasa.repository;

import com.coresquad.paracasa.entity.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface PedidoRepo extends CrudRepository<Pedido, Integer> {
    @Query("SELECT id FROM Pedido p WHERE p.user = id_user")
    public ArrayList<Integer> getPedidoByUserId(@Param("user") Integer id_user);
}
