package com.movierental.controller;

import com.movierental.model.Client;
import com.movierental.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    List<Client> getClients() {
        return clientService.getClients();
    }

    @PostMapping
    @Operation(summary = "Register a new client", tags = {"Client",},
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Returns the new Client",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Client.class)))
            })
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

}
