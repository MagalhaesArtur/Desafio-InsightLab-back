package com.desafioInsightLab.repositories;


import com.desafioInsightLab.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface supplierRepository  extends JpaRepository<Supplier, String> {
    Optional<Supplier> findUserByDocument(String document);

    Optional<Supplier> findUserById(String id);

}
