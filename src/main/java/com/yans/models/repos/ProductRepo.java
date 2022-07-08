package com.yans.models.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.yans.models.entities.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {

    List<Product> findByNamaContains(String name);

}
