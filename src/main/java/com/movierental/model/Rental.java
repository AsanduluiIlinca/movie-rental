package com.movierental.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany
    @JoinTable(name = "rental_movie", joinColumns = @JoinColumn(name = "rental_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies = new HashSet<>();

    private LocalDate rentDate = LocalDate.now();

    private LocalDate dueDate;

    private LocalDate returnDate;

    private boolean finalized;

    public void finalizeRental() {
        finalized = true;
        returnDate = LocalDate.now();
    }

    public boolean containsMovie(Long movieId) {
        return movies.stream().anyMatch(movie -> movie.getId().equals(movieId));
    }

}
