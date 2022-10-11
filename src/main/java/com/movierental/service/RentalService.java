package com.movierental.service;

import com.movierental.api.RentalRequest;
import com.movierental.model.Rental;

public interface RentalService {
    Rental createRental(RentalRequest rentalRequest);

    void endRental(Long id);
}
