package com.yans.models.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.yans.models.entities.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {

    Supplier findByEmail(String email);

    List<Supplier> findByNameContainsOrderByIdAsc(String name);

    List<Supplier> findByNameStartingWith(String prefix);

    List<Supplier> findByNameContainsOrEmailContains(String name, String email);

}
