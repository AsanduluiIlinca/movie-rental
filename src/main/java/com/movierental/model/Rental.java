package com.movierental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rentals")
    private Client client;

    @ManyToMany
    @JoinTable(name = "rental_movie", joinColumns = @JoinColumn(name = "rental_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies;

    private Date rentedDate;

    private Date dueDate;

    private Date returnedDate;

}
