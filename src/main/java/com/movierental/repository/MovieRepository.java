package com.movierental.repository;

import com.movierental.model.Genre;
import com.movierental.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, MovieRepositoryCustom {
    Optional<Movie> findById(Long id);

    List<Movie> findByGenre(Genre genre);

    @Query(value = "SELECT movie.title\n" +
            "FROM movie\n" +
            "LEFT JOIN rental_movie ON rental_movie.movie_id = movie.id\n" +
            "left join rental on rental_movie.rental_id = rental.id\n" +
            "group by movie.id\n" +
            "order by coalesce(sum(coalesce(rental.return_date, CURRENT_DATE) - rental.rent_date), 0) desc NULLS LAST", nativeQuery = true)
    List<String> searchForPopularMovies();
}
