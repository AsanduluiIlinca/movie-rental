package com.movierental.service.impl;

import com.movierental.exception.MovieNotFound;
import com.movierental.model.Movie;
import com.movierental.repository.MovieRepository;
import com.movierental.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    public ResponseEntity<Movie> findById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseThrow(MovieNotFound::new);
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

    @Override
    public List<Movie> searchMovieByTitle(String movieTitle) {
        return movieRepository.searchMoviesByTitle(movieTitle);
    }

    @Override
    public List<Movie> searchMoviesByDescription(String movieDescription) {
        return movieRepository.searchMoviesByDescription(movieDescription);
    }

    @Override
    public List<String> searchForPopularMovies() {
        return movieRepository.searchForPopularMovies();
    }

    @ExceptionHandler(MovieNotFound.class)
    public ResponseEntity<Object> handleException() {
        return new ResponseEntity<>("Movie not found!", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
