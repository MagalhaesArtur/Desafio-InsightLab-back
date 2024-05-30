package com.desafioInsightLab.services;


import com.desafioInsightLab.domain.Supplier;
import com.desafioInsightLab.dtos.SupplierDTO;
import com.desafioInsightLab.dtos.SupplierWithIdDTO;
import com.desafioInsightLab.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository repository;


    public Supplier findSupplierById(UUID id) throws Exception {
        return repository.findSupplierById(id).orElseThrow(()-> new Exception("Fornecedor não encontrado"));
    }

    public void saveSupplier(Supplier supplier) throws Exception {
        repository.save(supplier);
    }

    public Supplier createSupplier(SupplierDTO data){
        Supplier newSupplier = new Supplier(data);
        repository.save(newSupplier);
        return newSupplier;
    }

    public Supplier deleteSupplier(UUID id) throws Exception {
        Optional<Supplier> supplierAux = repository.findById(id);
        if(supplierAux.isPresent()){
            repository.deleteById(id);
            return supplierAux.get();
        }else{
            throw new Exception("Fornecedor não encontrado!");
        }
    }

    public Supplier editSupplier(SupplierWithIdDTO supplier) throws Exception {
        UUID uuidd = UUID.fromString(supplier.id());

        Optional<Supplier> supplierAux = repository.findSupplierById(uuidd);
        if(supplierAux.isPresent()){
            Supplier oldSupplier = supplierAux.get();
            oldSupplier.setName(supplier.name());
            oldSupplier.setDocument(supplier.document());
            oldSupplier.setEmail(supplier.email());
            return repository.save(oldSupplier);
        }else{
            throw new Exception("Fornecedor não encontrado!");
        }
    }


    public List<Supplier> getAllSuppliers(){
        return repository.findAll();
    }
}
