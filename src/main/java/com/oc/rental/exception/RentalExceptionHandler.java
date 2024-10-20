package com.oc.rental.exception;

import com.oc.rental.dto.HttpMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RentalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public HttpMessageDto handleNotFoundException(NotFoundException e) {
        return new HttpMessageDto(e.getMessage());
    }
}
