package com.oc.rental.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RentalCreationDto {
  private String name;
  private int surface;
  private int price;
  private  String description;
  private MultipartFile picture;

}
