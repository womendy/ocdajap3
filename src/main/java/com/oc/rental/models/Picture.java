package com.oc.rental.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    @ManyToOne
    private Rental rental;



}
