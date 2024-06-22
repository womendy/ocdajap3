package com.oc.rental.dto;

import lombok.Data;

@Data
public class HttpMessageDto {
  private String message;
  public HttpMessageDto(String message) {
    this.message = message;
  }
}
