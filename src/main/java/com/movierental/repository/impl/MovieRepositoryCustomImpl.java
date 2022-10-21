package com.movierental.repository.impl;

import com.movierental.model.Movie;
import com.movierental.repository.MovieRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MovieRepositoryCustomImpl implements MovieRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Movie> searchMoviesByTitle(String movieTitle) {

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);

        Root<Movie> movie = criteriaQuery.from(Movie.class);
        criteriaQuery.where(criteriaBuilder.like(criteriaBuilder.lower(movie.<String>get("title")), criteriaBuilder.lower(criteriaBuilder.parameter(String.class, "likeCondition"))));

        TypedQuery<Movie> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setParameter("likeCondition", "%" + movieTitle + "%");

        return typedQuery.getResultList();
    }

    @Override
    public List<Movie> searchMoviesByDescription(String description) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);

        Root<Movie> movie = criteriaQuery.from(Movie.class);
        criteriaQuery.where(criteriaBuilder.like(criteriaBuilder.lower(movie.<String>get("description")), criteriaBuilder.lower(criteriaBuilder.parameter(String.class, "likeCondition"))));

        TypedQuery<Movie> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setParameter("likeCondition", "%" + description + "%");

        return typedQuery.getResultList();
    }
}
