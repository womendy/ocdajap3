package com.oc.rental.controller;

import com.oc.rental.dto.HttpMessageDto;
import com.oc.rental.dto.RentalDto;
import com.oc.rental.models.Message;
import com.oc.rental.models.Rental;
import com.oc.rental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
  private RentalService rentalService;

  @Autowired
  public RentalController(RentalService rentalService) {
    this.rentalService = rentalService;
  }

  @GetMapping
  public List<Rental> getAllRental() {
    return rentalService.getAllRental().get();
  }

  @GetMapping("/{id}")
  public Rental getByid(@PathVariable(value = "id",required = false) long id) {
    return rentalService.getRentalById(id).orElseThrow();
  }

  @PostMapping("/rentals")
  public HttpMessageDto createRental(@RequestBody Rental rental) {
    return rentalService.createRental(rental).map(r->new HttpMessageDto("Rental created !")).orElseThrow();
  }

  @PutMapping("/{id}")
  public HttpMessageDto updateRental(@PathVariable("id") long id, @RequestBody RentalDto updatedRental) {
    return rentalService.updateRental(id, updatedRental).map(r->new HttpMessageDto("Rental updated !")).orElseThrow();

  }
}
