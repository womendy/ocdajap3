package com.oc.rental.service.impl;

import com.oc.rental.dto.RentalDto;
import com.oc.rental.models.Rental;
import com.oc.rental.repository.impl.RentalRepository;
import com.oc.rental.repository.impl.UserRepository;
import com.oc.rental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {
  private RentalRepository rentalRepository;
  private UserRepository userRepository;


  @Autowired
  public RentalServiceImpl(RentalRepository rentalRepository, UserRepository userRepository) {
    this.rentalRepository = rentalRepository;
    this.userRepository = userRepository;
  }

  @Override
  public Optional<Rental> getRentalById(long id) {
    return rentalRepository.findById(id);
  }

  @Override
  public Optional<List<Rental>> getAllRental() {
    List<Rental> rentals = new ArrayList<>();
    rentalRepository.findAll().forEach(rentals::add);
    return Optional.of(rentals);
  }

  @Override
  public Optional<Rental> createRental(Rental rental) {
    return Optional.of(rentalRepository.save(rental));
  }

  @Override
  public Optional<Rental> updateRental(long id, RentalDto updatedRental) {
    // Retrieve the existing rental by ID
    Rental existingRental = rentalRepository.findById(id).orElseThrow();

    // Update the properties of the existing rental with the values from the updatedRental object
    existingRental.setSurface(updatedRental.getSurface());
    existingRental.setPrice(updatedRental.getPrice());
    existingRental.setName(updatedRental.getName());
    existingRental.setDescription(updatedRental.getDescription());
    existingRental.setUpdated_at(LocalDate.now());

    // Update other properties as needed

    // Save the updated rental
    return Optional.of(rentalRepository.save(existingRental));
  }
}
