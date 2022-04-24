package com.coresquad.paracasa.repository;

import com.coresquad.paracasa.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends CrudRepository<User, Integer> {
    @Query("SELECT id FROM User u WHERE u.usuario = :usuario")
    public Integer getUserByUsername(@Param("usuario") String usuario);
}
