package com.movierental.service.impl;

import com.movierental.model.Client;
import com.movierental.repository.ClientRepository;
import com.movierental.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
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

}
