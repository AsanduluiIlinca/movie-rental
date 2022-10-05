package com.movierental.service.impl;

import com.movierental.model.Movie;
import com.movierental.repository.MovieRepository;
import com.movierental.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    final private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
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
