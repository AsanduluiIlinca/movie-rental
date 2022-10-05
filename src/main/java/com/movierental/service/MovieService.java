package com.movierental.service;

import com.movierental.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getMovies();

    Movie save(Movie newMovie);

    Movie update(Movie newMovie, Long id);

    void deleteById(Long id);

}
