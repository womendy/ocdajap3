package com.oc.rental.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "core_entity")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CoreEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    protected LocalDate created_at;
    protected LocalDate updated_at;
}


