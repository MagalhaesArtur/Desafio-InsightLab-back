package com.desafioInsightLab.controllers;


import com.desafioInsightLab.domain.Supplier;
import com.desafioInsightLab.dtos.IdDTO;
import com.desafioInsightLab.dtos.SupplierDTO;
import com.desafioInsightLab.dtos.SupplierWithIdDTO;
import com.desafioInsightLab.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierServices;

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody SupplierDTO supplier){
        Supplier newSupplier = supplierServices.createSupplier(supplier);
        return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers(){
        List<Supplier> suppliers = supplierServices.getAllSuppliers();
        return new ResponseEntity<>(suppliers ,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Supplier> deleteSupplier(@RequestBody IdDTO id) throws Exception {
        UUID uuidd = UUID.fromString(id.id());
        Supplier aux = supplierServices.deleteSupplier(uuidd);
        return new ResponseEntity<>(aux, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Supplier> editSupplier(@RequestBody SupplierWithIdDTO supplierDTO) throws Exception {
        Supplier aux = supplierServices.editSupplier(supplierDTO);

        return new ResponseEntity<>(aux, HttpStatus.OK);

    }


}
