package com.oc.rental.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Rental extends CoreEntity {
  @Column(nullable = false)
  private int surface;
  @Column(nullable = false)
  private int price;
  @OneToMany(mappedBy = "rental")
  private List<Picture> picture;
  @OneToMany(mappedBy = "rental")
  private List<Message> messages;
  @Column(nullable = false)
  private String description;
  @ManyToOne
  private User owner;

}
