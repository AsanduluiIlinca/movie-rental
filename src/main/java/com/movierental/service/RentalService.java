package com.movierental.service;

import com.movierental.api.RentalRequest;
import com.movierental.model.Rental;

import java.util.List;

public interface RentalService {
    Rental createRental(RentalRequest rentalRequest);

    void endRental(Long id);

    List<String> lastRentalMovies();
}
