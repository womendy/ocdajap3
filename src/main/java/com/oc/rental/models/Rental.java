package com.oc.rental.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@Entity
public class Rental extends CoreEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column(nullable = false)
  private int surface;
  @Column(nullable = false)
  private int price;
  private String picture;
  @Column(nullable = false)
  private String description;
  @ManyToOne
  private User owner;


}
