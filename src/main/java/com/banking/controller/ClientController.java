package com.banking.controller;

import com.banking.entity.Client;
import com.banking.entity.entityenumerations.ClientStatus;
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
    public Client createClient(@RequestBody Client client){
        return clientService.createClient(client);
    }

    @GetMapping("/find/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client findById(@PathVariable UUID id){
        return clientService.findById(id);
    }

    @GetMapping("/find/full-name/")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Client> findClientByFirstNameAndLastName(
            @RequestParam String firstName, @RequestParam String lastName){
        return clientService.findClientByFirstNameAndLastName(firstName,lastName);
    }

    @GetMapping("/find/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> findClientsByStatus(@PathVariable ClientStatus status){
        return clientService.findClientsByStatus(status);
    }

    @PostMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client editClient(@PathVariable UUID id, @RequestBody Client clientFE){
        return clientService.editClient(id,clientFE);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client deleteClientById(@PathVariable UUID id){
        return clientService.deleteClientById(id);
    }

    @PostMapping("/delete/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> deleteClientsByStatus(@PathVariable ClientStatus status){
        return clientService.deleteClientsByStatus(status);
    }

    @PostMapping("/edit/e-mail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client editEmailById(@PathVariable UUID id, @RequestParam String eMail){
        return clientService.editEmailById(id, eMail);
    }

    @PostMapping("/edit/phone/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client editPhoneById(@PathVariable UUID id, @RequestParam String phone){
        return clientService.editPhoneById(id, phone);
    }

}
