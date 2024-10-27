package com.oc.rental.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class User extends CoreEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Email
  @Column(nullable = false, unique = true)
  private String email;
  @OneToMany(mappedBy = "owner")
  private List<Rental> rentalList;
  @OneToMany(mappedBy = "user")
  private List<Message> messages;
  @Column(nullable = false)
  private String password;


}
