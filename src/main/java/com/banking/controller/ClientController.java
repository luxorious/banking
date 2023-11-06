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

/**
 * REST controller for managing clients.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/client")
public class ClientController {

    private final ClientService clientService;

    /**
     * Create a new client associated with the provided manager ID.
     *
     * @param client    The client entity to create.
     * @param managerId The ID of the manager associated with the client.
     * @return The created client entity.
     */
    @PostMapping("/create/{managerId}")
    @ResponseStatus(HttpStatus.OK)
    public Client createClient(@RequestBody Client client, @PathVariable UUID managerId){
        return clientService.createClient(client, managerId);
    }

    /**
     * Find a client by their ID.
     *
     * @param id The ID of the client to find.
     * @return The client entity with the given ID, or null if no client is found.
     */
    @GetMapping("/find/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client findById(@PathVariable UUID id){
        return clientService.findById(id);
    }

    /**
     * Find a client by their first name and last name.
     *
     * @param firstName The first name of the client.
     * @param lastName  The last name of the client.
     * @return An optional containing the client entity with the given first name and last name,
     *         or an empty optional if no client is found.
     */
    @GetMapping("/find/full-name/")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Client> findClientByFirstNameAndLastName(
            @RequestParam String firstName, @RequestParam String lastName){
        return clientService.findClientByFirstNameAndLastName(firstName,lastName);
    }

    /**
     * Find clients by their status.
     *
     * @param status The status to filter the clients.
     * @return A list of clients that match the provided status.
     */
    @GetMapping("/find/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> findClientsByStatus(@PathVariable ClientStatus status){
        return clientService.findClientsByStatus(status);
    }

    /**
     * Edit an existing client by their ID using the updated client details.
     *
     * @param id        The ID of the client to edit.
     * @param clientFE  The updated client details received from the front-end.
     * @return The edited client entity, or null if no client was found with the given ID.
     */
    @PostMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client editClient(@PathVariable UUID id, @RequestBody Client clientFE){
        return clientService.editClient(id,clientFE);
    }

    /**
     * Delete a specific client by their ID.
     *
     * @param id The ID of the client to delete.
     * @return The deleted client entity, or null if no client was found with the given ID.
     */
    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client deleteClientById(@PathVariable UUID id){
        return clientService.deleteClientById(id);
    }

    /**
     * Delete all clients with the provided status.
     *
     * @param status The status to filter the clients.
     * @return A list of deleted clients that match the provided status.
     */
    @PostMapping("/delete/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> deleteClientsByStatus(@PathVariable ClientStatus status){
        return clientService.deleteClientsByStatus(status);
    }

    /**
     * Edit the email of an existing client by their ID.
     *
     * @param id     The ID of the client to edit.
     * @param eMail  The new email to be set for the client.
     * @return The updated client entity, or null if no client was found with the given ID.
     */
    @PostMapping("/edit/e-mail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client editEmailById(@PathVariable UUID id, @RequestParam String eMail){
        return clientService.editEmailById(id, eMail);
    }

    /**
     * Edit the phone number of an existing client by their ID.
     *
     * @param id     The ID of the client to edit.
     * @param phone  The new phone number to be set for the client.
     * @return The updated client entity, or null if no client was found with the given ID.
     */
    @PostMapping("/edit/phone/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client editPhoneById(@PathVariable UUID id, @RequestParam String phone){
        return clientService.editPhoneById(id, phone);
    }

}
