package com.oc.rental.mapper;

import com.oc.rental.dto.RentalDto;
import com.oc.rental.models.Rental;
import com.oc.rental.models.User;

public class RentalMapper {
    public static RentalDto toDto(Rental rental) {
        if (rental == null) {
            return null;
        }

        RentalDto rentalDto = new RentalDto()
                .setId(rental.getId())
                .setName(rental.getName())
                .setSurface(rental.getSurface())
                .setPrice(rental.getPrice())
                .setDescription(rental.getDescription())
                .setPicture(rental.getPicture());

        // Assuming owner can be null
        if (rental.getOwner() != null) {
            rentalDto.setOwnerId(rental.getOwner().getId());
        }

        return rentalDto;
    }

    // Convert RentalDto to Rental entity
    public static Rental toEntity(RentalDto rentalDto, User owner) {
        if (rentalDto == null) {
            return null;
        }

        Rental rental = new Rental();
        rental.setId(rentalDto.getId());
        rental.setName(rentalDto.getName());
        rental.setSurface(rentalDto.getSurface());
        rental.setPrice(rentalDto.getPrice());
        rental.setDescription(rentalDto.getDescription());
        rental.setPicture(rentalDto.getPicture());
        rental.setOwner(owner);

        return rental;
    }
}
