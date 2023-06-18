package com.banking.controller;

import com.banking.entity.Client;
import com.banking.entity.entityEnumerations.ClientStatus;
import com.banking.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Client createClient(Client client){
        return clientService.createClient(client);
    }

    @GetMapping("/find/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Client> findById(@PathVariable UUID id){
        return clientService.findById(id);
    }

    @GetMapping("/find/full-name/")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Client> findClientByFirstNameAndLastName(
            @RequestParam String firstName, @RequestParam String lastName){
        return clientService.findClientByFirstNameAndLastName(firstName,lastName);
    }

    @GetMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> findClientsByStatus(ClientStatus status){
        return clientService.findClientsByStatus(status);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Client editClient(UUID id, Client clientFE){
        return clientService.editClient(id,clientFE);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Client deleteClientById(UUID id){
        return clientService.deleteClientById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> deleteClientsByStatus(ClientStatus status){
        return clientService.deleteClientsByStatus(status);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Client editEmailById(UUID id, String eMail){
        return clientService.editEmailById(id, eMail);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Client editPhoneById(UUID id, String phone){
        return clientService.editPhoneById(id, phone);
    }

}
