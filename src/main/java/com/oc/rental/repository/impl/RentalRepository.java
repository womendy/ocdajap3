package com.oc.rental.repository.impl;

import com.oc.rental.models.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface RentalRepository extends CrudRepository<Rental,Long> {
}
