package com.oc.rental.dto;

import lombok.Data;

import java.time.LocalDate;
//mapstruck
@Data
public class RentalDto {
  private long id;
  private String name;
  private int surface;
  private int price;
  private  String description;
  private long owner_id;

}
