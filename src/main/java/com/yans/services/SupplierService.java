package com.yans.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yans.models.entities.Supplier;
import com.yans.models.repos.SupplierRepo;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    public Supplier save(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public Supplier findOne(Long id) {
        Optional<Supplier> supplier = supplierRepo.findById(id);
        if (!supplier.isPresent()) {
            return null;
        }
        return supplier.get();
    }

    public Iterable<Supplier> findALl() {
        return supplierRepo.findAll();
    }

    public void removeOne(Long id) {
        supplierRepo.deleteById(id);
    }

    public Supplier findbByEmail(String email) {
        return supplierRepo.findByEmail(email);
    }

    public List<Supplier> findByName(String name) {
        return supplierRepo.findByNameContainsOrderByIdAsc(name);
    }

    public List<Supplier> findByNameStartWith(String prefix) {
        return supplierRepo.findByNameStartingWith(prefix);
    }

    public List<Supplier> findByNameOrEmailContains(String keyword) {
        return supplierRepo.findByNameContainsOrEmailContains(keyword, keyword);
    }
}
