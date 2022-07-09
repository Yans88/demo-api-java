package com.yans.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yans.dto.ResponseData;
import com.yans.dto.SupplierData;
import com.yans.models.entities.Supplier;
import com.yans.services.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        /*
         * Supplier supplier = new Supplier();
         * supplier.setName(supplierData.getName());
         * supplier.setAddress(supplierData.getAddress());
         * supplier.setEmail(supplierData.getEmail());
         */

        Supplier supplier = modelMapper.map(supplierData, Supplier.class);
        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Supplier> findAll() {
        return supplierService.findALl();
    }

    @GetMapping("/{id}")
    public Supplier findOne(@PathVariable("id") Long id) {
        return supplierService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierData supplierData, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        /*
         * Supplier supplier = new Supplier();
         * supplier.setName(supplierData.getName());
         * supplier.setAddress(supplierData.getAddress());
         * supplier.setEmail(supplierData.getEmail());
         */

        Supplier supplier = modelMapper.map(supplierData, Supplier.class);
        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/search_email")
    public Supplier findByEmail(@RequestBody SupplierData supplierData) {
        return supplierService.findbByEmail(supplierData.getKeyword());
    }

    @PostMapping("/search_name")
    public List<Supplier> findByName(@RequestBody SupplierData supplierData) {
        return supplierService.findByName(supplierData.getKeyword());
    }

    @PostMapping("/search_name_with")
    public List<Supplier> findByNameStartWith(@RequestBody SupplierData supplierData) {
        return supplierService.findByNameStartWith(supplierData.getKeyword());
    }

    @PostMapping("/search_name_email")
    public List<Supplier> findByNameOrEmail(@RequestBody SupplierData supplierData) {
        return supplierService.findByNameOrEmailContains(supplierData.getKeyword());
    }
}
