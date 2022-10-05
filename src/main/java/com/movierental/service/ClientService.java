package com.movierental.service;

import com.movierental.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> getClients();

    Client save(Client newClient);

    Client update(Client newClient, Long id);

    void deleteById(Long id);

}
