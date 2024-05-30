package com.desafioInsightLab.repositories;


import com.desafioInsightLab.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
    Optional<Supplier> findSupplierByDocument(String document);

    Optional<Supplier> findSupplierById(UUID id);

}
