package com.movierental.service.impl;

import com.movierental.api.RentalRequest;
import com.movierental.model.Rental;
import com.movierental.repository.ClientRepository;
import com.movierental.repository.MovieRepository;
import com.movierental.repository.RentalRepository;
import com.movierental.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {

    public static Integer DAYS_TO_RENT = 7;
    final private RentalRepository rentalRepository;
    final private MovieRepository movieRepository;
    final private ClientRepository clientRepository;

    @Override
    public Rental createRental(RentalRequest rentalRequest) {
        var rental = new Rental();

        clientRepository.findById(rentalRequest.getClientId()).ifPresent(rental::setClient);
        rental.setDueDate(LocalDate.now().plusDays(DAYS_TO_RENT));
        movieRepository.findById(rentalRequest.getMovieId()).ifPresent(movie -> rental.getMovies().add(movie));

        return rentalRepository.save(rental);
    }

    @Transactional
    @Override
    public void endRental(Long id) {
        rentalRepository.findById(id).ifPresent(Rental::finalizeRental);
    }

    @Override
    public List<String> lastRentalMovies() {
        return rentalRepository.lateRentalMovies();
    }

}
