package com.oc.rental.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter

public class CoreEntity implements Serializable {
    private String name;
    private LocalDate created_at;
    private LocalDate updated_at;
}
