package com.movierental.controller;

import com.movierental.model.Movie;
import com.movierental.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    ResponseEntity<Movie> findById(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping
    Movie addMovie(@RequestBody Movie newMovie) {
        return movieService.save(newMovie);
    }

    @PutMapping("{id}")
    Movie updateMovie(@RequestBody Movie newMovie, @PathVariable Long id) {
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

    @GetMapping("popular")
    List<String> getRatedMovies() {
        return movieService.searchForPopularMovies();
    }

}

