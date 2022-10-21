package com.movierental.service;

import com.movierental.model.Movie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {

    List<Movie> getMovies();

    ResponseEntity<Movie> findById(Long id);

    Movie save(Movie newMovie);

    Movie update(Movie newMovie, Long id);

    void deleteById(Long id);

    List<Movie> searchMovieByTitle(String movieTitle);

    List<Movie> searchMoviesByDescription(String movieDescription);

    List<String> searchForPopularMovies();
}
