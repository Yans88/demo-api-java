package com.yans.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yans.dto.ProductData;
import com.yans.dto.ResponseData;
import com.yans.models.entities.Product;
import com.yans.models.entities.Supplier;
import com.yans.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors) {
        ResponseData<Product> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok().body(responseData);
    }

    @GetMapping
    public Iterable<Product> findALl() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable Long id) {
        return productService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable Long id) {
        productService.removeOne(id);
    }

    @PostMapping("/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId) {
        productService.addSupplier(supplier, productId);
    }

    @PostMapping("/search")
    public Product geProductByName(@RequestBody ProductData productData) {
        return productService.findProductByname(productData.getKeyword());
    }

    @PostMapping("/search_like")
    public List<Product> geProductByNameLike(@RequestBody ProductData productData) {
        return productService.findProductByLikeName(productData.getKeyword());
    }

    @GetMapping("/search_category/{categoryId}")
    public List<Product> geProductByCategory(@PathVariable("categoryId") Long categoryId) {
        return productService.findProductByCategory(categoryId);
    }

    @GetMapping("/search_supplier/{supplierId}")
    public List<Product> geProductBySupplier(@PathVariable("supplierId") Long supplierId) {
        return productService.findProductBySupplier(supplierId);
    }

}
