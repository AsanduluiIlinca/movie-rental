package com.movierental.repository.impl;

import com.movierental.model.Client;
import com.movierental.model.Rental;
import com.movierental.repository.ClientRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ClientRepositoryCustomImpl implements ClientRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> searchClientByName(String name) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);

        Root<Client> client = criteriaQuery.from(Client.class);
        criteriaQuery.where(criteriaBuilder.like(criteriaBuilder.lower(client.get("name")), criteriaBuilder.lower(criteriaBuilder.parameter(String.class, "likeCondition"))));

        TypedQuery<Client> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setParameter("likeCondition", "%" + name + "%");
        return typedQuery.getResultList();
    }

    @Override
    public List<String> searchActiveClientsCustom() {
        //work in progress
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        final Root<Client> clientsNameRoot = criteriaQuery.from(Client.class);
        Join<Client, Rental> rentalJoin = clientsNameRoot.join("rental", JoinType.LEFT);
        rentalJoin.on(criteriaBuilder.equal(clientsNameRoot.get("client_id"), rentalJoin.get("rental_id")));
//        criteriaQuery.groupBy(clientsNameRoot.get(Client_.id));
        criteriaQuery.select(rentalJoin.get("name"));
        TypedQuery<String> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }
}
