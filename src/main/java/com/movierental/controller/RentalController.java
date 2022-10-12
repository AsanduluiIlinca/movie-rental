package com.movierental.controller;

import com.movierental.api.RentalRequest;
import com.movierental.model.Rental;
import com.movierental.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rental")
@AllArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    Rental rentMovie(@RequestBody RentalRequest rentalRequest) {
        return rentalService.createRental(rentalRequest);
    }

    @PutMapping("{id}")
    void returnMovie(@PathVariable Long id) {
        rentalService.endRental(id);
    }

    @GetMapping("/late")
    List<String> lateRentalMovies() {
        return rentalService.lastRentalMovies();
    }

}
