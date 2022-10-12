package com.movierental.service;

import com.movierental.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> getMovies();

    Optional<Movie> findById(Long id);

    Movie save(Movie newMovie);

    Movie update(Movie newMovie, Long id);

    void deleteById(Long id);

    List<Movie> searchMovieByTitle(String movieTitle);

    List<Movie> searchMoviesByDescription(String movieDescription);

    List<String> searchForPopularMovies();
}
