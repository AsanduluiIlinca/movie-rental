package com.movierental.controller;

import com.movierental.model.Client;
import com.movierental.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("client")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping("{id}")
    ResponseEntity<Client> findById(@PathVariable Long id) {
        Optional<Client> client = clientService.findById(id);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    Client addClient(@RequestBody Client newClient) {
        return clientService.save(newClient);
    }

    @PutMapping("{id}")
    Client updateClient(@RequestBody Client newClient, @PathVariable Long id) {

        return clientService.update(newClient, id);
    }

    @DeleteMapping("{id}")
    void deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
    }

    @GetMapping("/search/{clientName}")
    List<Client> searchClientByName(@PathVariable String clientName) {
        return clientService.searchClientByName(clientName);
    }

    @GetMapping("active")
    List<String> searchActiveClients() {
        return clientService.searchActiveClients();
    }

}
