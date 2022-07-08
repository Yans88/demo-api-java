package com.yans.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.yans.models.entities.Category;

public interface CategoryRepo extends CrudRepository<Category, Long> {

}
