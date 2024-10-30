package com.oc.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.jackson.Jacksonized;
import org.springframework.lang.NonNull;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Jacksonized
@Accessors(chain = true)
public class RentalDto implements Serializable {
  private long id;
  @NonNull
  private String name;
  @NonNull
  private int surface;
  @NonNull
  private int price;
  @NonNull
  private  String description;
  private long ownerId;
  private String picture;

}
