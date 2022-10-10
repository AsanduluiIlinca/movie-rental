package com.movierental.service.impl;

import com.movierental.model.Movie;
import com.movierental.model.Rental;
import com.movierental.repository.ClientRepository;
import com.movierental.repository.MovieRepository;
import com.movierental.repository.RentalRepository;
import com.movierental.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {

    public Integer DAYS_TO_RENT = 7;

    final private RentalRepository rentalRepository;
    final private MovieRepository movieRepository;

    final private ClientRepository clientRepository;


    public boolean checkMovieAvailability(Movie movie) {

        return rentalRepository.findAll().stream().filter(rental -> rental.containsMovie(movie.getId())).allMatch(rental -> rental.getDueDate() != null);

    }

    public void rentMovie(String movieTitle, Long clientId) {

        Movie movie = movieRepository.findAll().stream().filter(movie1 -> movie1.getTitle().equals(movieTitle)).findAny().orElse(null);

        if (!checkMovieAvailability(movie)) {
            throw new RuntimeException("Movie already rented");
        }

        Rental rental = new Rental();

        rental.setClient(clientRepository.findById(clientId).get());
        rental.getMovies().add(movieRepository.findByTile(movieTitle));
        rental.setRentedDate(LocalDate.now());
        rental.setDueDate(LocalDate.now().plusDays(DAYS_TO_RENT));
        rental.setRentedDate(null);

        rentalRepository.findAll().add(rental);

    }

    public void returnMovie(String movieTitle, Long clientId) {
        Movie movie = movieRepository.findAll().stream().filter(movie1 -> movie1.getTitle().equals(movieTitle)).findAny().orElse(null);

        Rental rent = rentalRepository.findAll().stream().filter(rental -> rental.containsMovie(movie.getId()) && rental.getClient().getId().equals(clientId)).filter(rental -> rental.getReturnedDate() == null).findAny().orElse(null);
        rent.setReturnedDate(LocalDate.now());
    }


}
