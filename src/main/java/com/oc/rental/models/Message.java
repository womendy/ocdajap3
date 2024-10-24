package com.oc.rental.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity(name = "message")
@NoArgsConstructor
@Getter
@Setter
public class Message implements Serializable {
  private static final long serialVersionUID = -602903632167375369L;

    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String message;

  @ManyToOne
  private User user;

  @ManyToOne
  private Rental rental;


}
