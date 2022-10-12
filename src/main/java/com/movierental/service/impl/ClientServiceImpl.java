package com.movierental.service.impl;

import com.movierental.model.Client;
import com.movierental.repository.ClientRepository;
import com.movierental.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
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

}
