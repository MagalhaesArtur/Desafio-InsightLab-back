package com.desafioInsightLab.domain;


import com.desafioInsightLab.dtos.SupplierDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "suppliers")
@Table(name = "suppliers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private LocalDateTime registerDate;


    public Supplier(SupplierDTO supplier){
        setName(supplier.name());
        setRegisterDate(LocalDateTime.now());
        setDocument(supplier.document());
        setEmail(supplier.email());
    }
}
