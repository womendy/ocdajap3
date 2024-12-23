package com.oc.rental.service;

import com.oc.rental.dto.RentalCreationDto;
import com.oc.rental.dto.RentalDto;
import com.oc.rental.models.Rental;

import java.util.List;
import java.util.Optional;

public interface RentalService {

    public Optional<Rental> getRentalById(long id);
    public Optional<List<Rental>> getAllRental();
    public Optional<Rental> createRental(RentalCreationDto rentalCreationDto);
    public Optional<Rental> updateRental(long id, RentalDto rental);
    public Optional<List<RentalDto>> getRentalsByOwnerId(Long ownerId);
    public Optional<List<RentalDto>> getRentalsForAuthenticatedOwner();

}
