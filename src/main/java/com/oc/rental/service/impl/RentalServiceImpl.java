package com.oc.rental.service.impl;

import com.oc.rental.dto.RentalCreationDto;
import com.oc.rental.dto.RentalDto;
import com.oc.rental.mapper.RentalMapper;
import com.oc.rental.models.Rental;
import com.oc.rental.models.User;
import com.oc.rental.repository.impl.RentalRepository;
import com.oc.rental.repository.impl.UserRepository;
import com.oc.rental.service.AuthenticatedUserService;
import com.oc.rental.service.RentalService;
import com.oc.rental.utils.ImageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;


@Service
public class RentalServiceImpl implements RentalService {
    private RentalRepository rentalRepository;
    private UserRepository userRepository;
    private AuthenticatedUserService authenticatedUserService;

    private static final String BASE_URL = "http://localhost:8080/";


    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.authenticatedUserService = authenticatedUserService;

    }

    @Override
    public Optional<Rental> getRentalById(long id) {
        return rentalRepository.findById(id);
    }

    @Override
    public Optional<List<Rental>> getAllRental() {
        List<Rental> rentals = new ArrayList<>();
        rentalRepository.findAll().forEach(rentals::add);

        List<RentalDto> rentalDtos = rentals.stream()
                .map(RentalMapper::toDto)
                .peek(rentalDto -> {
                    if (rentalDto.getPicture() != null && !rentalDto.getPicture().startsWith("http")) {
                        rentalDto.setPicture(BASE_URL + rentalDto.getPicture());
                    }
                })
                .collect(Collectors.toList());

        return Optional.of(rentals);
    }

    @Override
    public Optional<List<RentalDto>> getRentalsByOwnerId(Long ownerId) {
        List<RentalDto> rentals = rentalRepository.findById(ownerId).stream()
                .map(RentalMapper::toDto)
                .peek(rentalDto -> {
                    if (rentalDto.getPicture() != null && !rentalDto.getPicture().startsWith("http")) {
                        rentalDto.setPicture(BASE_URL + rentalDto.getPicture());
                    }
                })
                .collect(Collectors.toList());
        return Optional.of(rentals);
    }


    @Override
    public Optional<List<RentalDto>> getRentalsForAuthenticatedOwner() {
        Long ownerId = authenticatedUserService.getAuthenticatedOwnerId();
        return getRentalsByOwnerId(ownerId);
    }

    @Override
    public Optional<Rental> createRental(RentalCreationDto rentalCreationDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            throw new RuntimeException("Unexpected principal type");
        }

        User userCreator = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create and save rental as before
        Rental rental = new Rental()
                .setSurface(rentalCreationDto.getSurface())
                .setPrice(rentalCreationDto.getPrice())
                .setDescription(rentalCreationDto.getDescription())
                .setOwner(userCreator)
                .setPicture(ImageHelper.saveImage(rentalCreationDto.getPicture()));

        rental.setName(rentalCreationDto.getName());
        LocalDate now = LocalDate.now();
        rental.setUpdated_at(now);
        rental.setCreated_at(now);

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

        // Save the updated rental
        return Optional.of(rentalRepository.save(existingRental));
    }
}
