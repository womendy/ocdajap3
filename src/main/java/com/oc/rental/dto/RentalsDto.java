package com.oc.rental.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class RentalsDto {
    List<RentalDto> rentals;

    public List<RentalDto> getRentals() {
        return rentals;
    }

    public RentalsDto setRentals(List<RentalDto> rentals) {
        this.rentals = rentals;
        return this;
    }
}