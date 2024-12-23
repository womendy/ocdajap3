package com.oc.rental.controller;

import com.oc.rental.configuration.JwtUtil;
import com.oc.rental.dto.HttpMessageDto;
import com.oc.rental.dto.RentalCreationDto;
import com.oc.rental.dto.RentalDto;
import com.oc.rental.dto.RentalsDto;
import com.oc.rental.exception.NotFoundException;
import com.oc.rental.mapper.RentalMapper;
import com.oc.rental.service.RentalService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
@SecurityRequirement(name = "bearerAuth")

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService, JwtUtil jwtUtil) {
        this.rentalService = rentalService;
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RentalsDto getAllRental() {
        return new RentalsDto().setRentals(
                rentalService.getAllRental()
                        .map(rentals -> rentals.stream().map(RentalMapper::toDto).toList())
                        .orElseGet(ArrayList::new));
    }
    @SecurityRequirement(name = "bearerAuth")

    @GetMapping("/{id}")
    public RentalDto getByid(@PathVariable(value = "id", required = false) Long id) throws NotFoundException {
        return rentalService.getRentalById(id).map(RentalMapper::toDto).
                orElseThrow(() -> new NotFoundException("Rental not found"));
    }

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public RentalDto createRental(@ModelAttribute RentalCreationDto rentalCreationDto) {
        return rentalService.createRental(rentalCreationDto).map(RentalMapper::toDto).orElseThrow();
    }

    @PutMapping("/{id}")
    public HttpMessageDto updateRental(@PathVariable("id") long id, @RequestBody RentalDto updatedRental) {
        return rentalService.updateRental(id, updatedRental).map(r -> new HttpMessageDto("Rental updated !")).orElseThrow();
    }
}
