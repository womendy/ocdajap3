package com.oc.rental.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserDto {
  private String name;
  @Email
  private String email;
  private String password;
}
