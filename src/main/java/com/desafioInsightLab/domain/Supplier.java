package com.desafioInsightLab.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "suppliers")
@Table(name = "suppliers")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private LocalDateTime registerDate;
}
