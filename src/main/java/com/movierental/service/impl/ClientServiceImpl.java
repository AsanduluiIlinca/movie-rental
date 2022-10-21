package com.movierental.service.impl;

import com.movierental.exception.ClientNotFound;
import com.movierental.model.Client;
import com.movierental.repository.ClientRepository;
import com.movierental.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public ResponseEntity<Client> findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseThrow(ClientNotFound::new);
    }

    @Override
    public Client save(Client newClient) {
        return clientRepository.save(newClient);
    }

    @Override
    public Client update(Client newClient, Long id) {
        return clientRepository.findById(id).map(
                        client -> {
                            client.setName(newClient.getName());
                            client.setRentals(newClient.getRentals());
                            return clientRepository.save(client);
                        })
                .orElseGet(() -> {
                    newClient.setId(id);
                    return clientRepository.save(newClient);
                });
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> searchClientByName(String name) {
        return clientRepository.searchClientByName(name);
    }

    @Override
    public List<String> searchActiveClients() {
        return clientRepository.searchForActiveClients();
    }

    @Override
    public List<String> searchActiveCustomClients() {
        return clientRepository.searchActiveClientsCustom();
    }

    @ExceptionHandler(ClientNotFound.class)
    public ResponseEntity<Object> handleException() {
        return new ResponseEntity<>("Client not found!", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
