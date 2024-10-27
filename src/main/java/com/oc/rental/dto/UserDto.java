package com.oc.rental.dto;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.Accessors;
import java.time.LocalDate;


@Data
public class UserDto {
  private long id;
  private String name;
  @Email
  private String email;
  private LocalDate created_at;
  private LocalDate updated_at;
  private String password;

  public UserDto(long id, String name, @Email String email) {
  }

  public UserDto() {

  }
}
