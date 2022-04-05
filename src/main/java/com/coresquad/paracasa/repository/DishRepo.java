package com.coresquad.paracasa.repository;

import com.coresquad.paracasa.entity.Dish;
import org.springframework.data.repository.CrudRepository;

public interface DishRepo extends CrudRepository<Dish, Integer> {
}
