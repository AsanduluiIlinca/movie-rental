package com.movierental.service;

import com.movierental.model.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {

    List<Client> getClients();

    ResponseEntity<Client> findById(Long id);

    Client save(Client newClient);

    Client update(Client newClient, Long id);

    void deleteById(Long id);

    List<Client> searchClientByName(String name);

    List<String> searchActiveClients();

    List<String> searchActiveCustomClients();

}
