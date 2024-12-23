package com.oc.rental.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class RentalsDto {
    List<RentalDto> rentals;
}
