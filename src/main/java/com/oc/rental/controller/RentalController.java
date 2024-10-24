package com.oc.rental.controller;

import com.oc.rental.configuration.JwtUtil;
import com.oc.rental.dto.HttpMessageDto;
import com.oc.rental.dto.RentalCreationDto;
import com.oc.rental.dto.RentalDto;
import com.oc.rental.exception.NotFoundException;
import com.oc.rental.mapper.RentalMapper;
import com.oc.rental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
  private final JwtUtil jwtUtil;
  private RentalService rentalService;

  @Autowired
  public RentalController(RentalService rentalService, JwtUtil jwtUtil) {
    this.rentalService = rentalService;
    this.jwtUtil = jwtUtil;
  }

  @GetMapping
  public List<RentalDto> getAllRental() {
    return rentalService.getAllRental()
            .map(rentals -> rentals.stream().map(RentalMapper::toDto).toList())
            .orElseGet(Collections::emptyList);
  }

  @GetMapping("/{id}")
  public RentalDto getByid(@PathVariable(value = "id",required = false) Long id,@RequestHeader("Authorization") String token) throws NotFoundException {
    return rentalService.getRentalById(id).map(RentalMapper::toDto).
            orElseThrow(() -> new NotFoundException("Rental not found"));
  }

  @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
  public RentalDto createRental(@ModelAttribute RentalCreationDto rentalCreationDto) {
    return rentalService.createRental(rentalCreationDto).map(RentalMapper::toDto).orElseThrow();
  }

  @PutMapping("/{id}")
  public HttpMessageDto updateRental(@PathVariable("id") long id, @RequestBody RentalDto updatedRental) {
    return rentalService.updateRental(id, updatedRental).map(r->new HttpMessageDto("Rental updated !")).orElseThrow();
  }
}
