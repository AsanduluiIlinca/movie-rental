package com.movierental.repository;

import com.movierental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    Optional<Rental> findById(Long id);

    @Query(value = "SELECT movie.title\n" +
            "FROM movie\n" +
            "LEFT JOIN rental_movie ON rental_movie.movie_id = movie.id\n" +
            "left join rental on rental_movie.rental_id = rental.id\n" +
            "where (rental.return_date is null) and (due_date > CURRENT_DATE)\n" +
            "group by movie.id, rental.due_date, rent_date\n" +
            "order by (rental.due_date - rent_date) desc NULLS LAST", nativeQuery = true)
    List<String> lateRentalMovies();
}
