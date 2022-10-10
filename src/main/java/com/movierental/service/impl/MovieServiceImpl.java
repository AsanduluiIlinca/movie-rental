package com.movierental.service.impl;

import com.movierental.model.Movie;
import com.movierental.repository.MovieRepository;
import com.movierental.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    final private MovieRepository movieRepository;


    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie save(Movie newMovie) {
        return movieRepository.save(newMovie);
    }

    @Override
    public Movie update(Movie newMovie, Long id) {
        return movieRepository.findById(id).map(
                        movie -> {
                            movie.setTitle(newMovie.getTitle());
                            movie.setDescription(newMovie.getDescription());
                            movie.setGenre(newMovie.getGenre());
                            movie.setRentals(newMovie.getRentals());
                            return movieRepository.save(movie);
                        })
                .orElseGet(() -> {
                    newMovie.setId(id);
                    return movieRepository.save(newMovie);
                });
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }
}
