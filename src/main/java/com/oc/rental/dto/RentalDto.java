package com.oc.rental.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

//mapstruck
@Data
public class RentalDto {
  private long id;
  private String name;
  private int surface;
  private int price;
  private  String description;
  private long owner_id;
  private String picture;

}
