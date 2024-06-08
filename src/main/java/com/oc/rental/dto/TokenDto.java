package com.oc.rental.dto;

import lombok.RequiredArgsConstructor;

public class TokenDto {
  private String token;
  public TokenDto(String token) {
    this.token = token;
  }
}
