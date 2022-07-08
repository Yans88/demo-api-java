package com.yans.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.yans.models.entities.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {

}
