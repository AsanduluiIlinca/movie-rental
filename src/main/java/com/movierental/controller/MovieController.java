package com.movierental.controller;

import com.movierental.model.Client;
import com.movierental.model.Movie;
import com.movierental.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
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

    @PostMapping
    @Operation(summary = "Register a new client", tags = {"Client",},
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Returns the new Client",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Client.class)))
            })
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

}

