package com.movierental.controller;

import com.movierental.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    void rentMovie(@RequestBody String movieTitle, @RequestBody Long clientId) {
        rentalService.rentMovie(movieTitle, clientId);
    }

    @PutMapping
    void returnMovie(@RequestBody String movieTitle, @RequestBody Long clientId) {
        rentalService.returnMovie(movieTitle, clientId);
    }

}
