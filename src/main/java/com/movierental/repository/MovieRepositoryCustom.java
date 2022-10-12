package com.movierental.repository;

import com.movierental.model.Movie;
import com.movierental.model.RatedMovie;

import java.util.List;

public interface MovieRepositoryCustom {
    List<Movie> searchMoviesByTitle(String movieTitle);

    List<Movie> searchMoviesByDescription(String description);

    List<RatedMovie> searchMostRentedMovies();
}
