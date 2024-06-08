package com.oc.rental.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Data
@Entity(name = "core_entity")
@Inheritance(strategy = InheritanceType.JOINED)
public class CoreEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    private LocalDate created_at;
    private LocalDate updated_at;
}
