package com.movierental.repository;

import com.movierental.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, ClientRepositoryCustom {
    Optional<Client> findById(Long id);

    List<Client> findByName(String name);

    @Query(value = "SELECT client.name\n" +
            "FROM client\n" +
            "LEFT JOIN rental ON rental.client_id = client.id\n" +
            "group by client.id\n" +
            "order by coalesce(sum(coalesce(rental.return_date, CURRENT_DATE) - rental.rent_date), 0) desc NULLS LAST", nativeQuery = true)
    List<String> searchForActiveClients();
}
