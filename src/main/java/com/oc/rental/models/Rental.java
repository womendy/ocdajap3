package com.oc.rental.models;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "rental")
@ToString
public class Rental {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;
  protected LocalDate created_at;
  protected LocalDate updated_at;
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
