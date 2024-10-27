package com.oc.rental.models;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
public class CoreEntity implements Serializable {
    private String name;

    protected LocalDate created_at;
    protected LocalDate updated_at;
}


