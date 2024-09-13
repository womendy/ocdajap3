package com.oc.rental.controller;

import com.oc.rental.configuration.JwtUtil;
import com.oc.rental.dto.HttpMessageDto;
import com.oc.rental.dto.RentalCreationDto;
import com.oc.rental.dto.RentalDto;
import com.oc.rental.models.Rental;
import com.oc.rental.service.RentalService;
import com.oc.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
  private final JwtUtil jwtUtil;
  private RentalService rentalService;
  private UserService userService;

  @Autowired
  public RentalController(RentalService rentalService, JwtUtil jwtUtil) {
    this.rentalService = rentalService;
    this.jwtUtil = jwtUtil;
  }

  @GetMapping
  public List<Rental> getAllRental() {
    return rentalService.getAllRental().get();
  }

  @GetMapping("/{id}")
  public Rental getByid(@PathVariable(value = "id",required = false) long id,@RequestHeader("Authorization") String token) {
    return rentalService.getRentalById(id).orElseThrow();
  }

  @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
  public HttpMessageDto createRental(@ModelAttribute RentalCreationDto rentalCreationDto) {
    return rentalService.createRental(rentalCreationDto).map(r->new HttpMessageDto("Rental created !")).orElseThrow();
  }

  @PutMapping("/{id}")
  public HttpMessageDto updateRental(@PathVariable("id") long id, @RequestBody RentalDto updatedRental) {
    return rentalService.updateRental(id, updatedRental).map(r->new HttpMessageDto("Rental updated !")).orElseThrow();
// update in database
  }
}
