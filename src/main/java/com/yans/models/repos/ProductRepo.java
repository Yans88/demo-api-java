package com.yans.models.repos;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.yans.models.entities.Product;
import com.yans.models.entities.Supplier;

public interface ProductRepo extends CrudRepository<Product, Long> {

    List<Product> findByNamaContains(String name);

    @Query("SELECT p from Product p where p.nama = :nama")
    public Product findProductByname(@PathParam("nama") String nama);

    @Query("SELECT p from Product p where p.nama LIKE :nama")
    public List<Product> findProductByNameLike(@PathParam("nama") String nama);

    @Query("SELECT p from Product p where p.category.id = :categoryId")
    public List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);

    @Query("SELECT p from Product p where :supplier MEMBER OF p.suppliers")
    public List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);

}
