package com.oc.rental.models;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Rental extends CoreEntity {

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
