package com.coresquad.paracasa.repository;

import com.coresquad.paracasa.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Integer> {
}
