package com.oc.rental.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class User extends CoreEntity {

  @Column(nullable = false, unique = true)
  private String email;
  @OneToMany(mappedBy = "owner")
  private List<Rental> rentalList;
  @OneToMany(mappedBy = "user")
  private List<Message> messages;
  @Column(nullable = false)
  private String password;
}
