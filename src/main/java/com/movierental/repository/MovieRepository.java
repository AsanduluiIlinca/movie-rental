package com.movierental.repository;

import com.movierental.model.Genre;
import com.movierental.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, MovieRepositoryCustom {
    Optional<Movie> findById(Long id);

    List<Movie> findByGenre(Genre genre);

}
