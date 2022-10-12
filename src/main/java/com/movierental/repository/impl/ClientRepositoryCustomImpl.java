package com.movierental.repository.impl;

import com.movierental.model.Client;
import com.movierental.repository.ClientRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        criteriaQuery.where(criteriaBuilder.like(criteriaBuilder.lower(client.<String>get("name")), criteriaBuilder.lower(criteriaBuilder.parameter(String.class, "likeCondition"))));

        TypedQuery<Client> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setParameter("likeCondition", "%" + name + "%");
        return typedQuery.getResultList();
    }
}
