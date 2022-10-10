package com.movierental.service;

public interface RentalService {
    void rentMovie(String movieTitle, Long clientId);

    void returnMovie(String movieTitle, Long clientId);
}
