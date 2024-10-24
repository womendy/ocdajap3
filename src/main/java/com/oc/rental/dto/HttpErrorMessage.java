package com.oc.rental.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class HttpErrorMessage extends HttpMessageDto {
   private HttpStatus status;
   public HttpErrorMessage(HttpStatus status, String message) {
       super(message);
       this.status = status;
   }
}
