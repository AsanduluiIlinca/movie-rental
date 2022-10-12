package com.movierental.service;

import com.movierental.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> getClients();

    Optional<Client> findById(Long id);

    Client save(Client newClient);

    Client update(Client newClient, Long id);

    void deleteById(Long id);

    List<Client> searchClientByName(String name);

    List<String> searchActiveClients();

}
