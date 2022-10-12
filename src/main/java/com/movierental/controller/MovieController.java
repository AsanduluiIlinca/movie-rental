package com.movierental.controller;

import com.movierental.model.Movie;
import com.movierental.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("movie")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("{id}")
    Optional<Movie> findById(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping
    Movie addClient(@RequestBody Movie newMovie) {
        return movieService.save(newMovie);
    }

    @PutMapping("{id}")
    Movie updateClient(@RequestBody Movie newMovie, @PathVariable Long id) {
        return movieService.update(newMovie, id);
    }

    @DeleteMapping("{id}")
    void deleteMovie(@PathVariable Long id) {
        movieService.deleteById(id);
    }

    @GetMapping("/search/{movieTitle}")
    @ResponseBody
    List<Movie> getMovieByTitle(@PathVariable String movieTitle) {
        return movieService.searchMovieByTitle(movieTitle);
    }

    @GetMapping("/{movieDescription}")
    List<Movie> getMovieByDescription(@PathVariable String movieDescription) {
        return movieService.searchMoviesByDescription(movieDescription);
    }

}

