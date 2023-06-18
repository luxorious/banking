package com.banking.service.implementation;

import com.banking.entity.Client;
import com.banking.entity.entityEnumerations.ClientStatus;
import com.banking.entity.entityEnumerations.DeletedStatus;
import com.banking.repository.ClientRepository;
import com.banking.service.interfaces.ClientService;
import com.banking.service.interfaces.utility.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final Converter<Client> clientConverter;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> findById(UUID uuid) {
        return clientRepository.findById(uuid);
    }

    @Override
    public Optional<Client> findClientByFirstNameAndLastName(String firstName, String lastName) {
        return clientRepository.findClientByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Client> findClientsByStatus(ClientStatus status) {
        return clientRepository.findClientsByStatus(status);
    }

    @Override
    public Client editClient(UUID id, Client clientFE) {
        Optional<Client> clientFromDB = clientRepository.findById(id);
        if (clientFromDB.isPresent()) {
            Client clientToUpdate = clientConverter.convertFields(clientFromDB.get(), clientFE);
            clientRepository.save(clientToUpdate);
            log.info("Client with client_ID: " + id + " updated successfully");
            return clientToUpdate;
        } else {
            log.info("Client with client_ID: " + id + " not found");
            return new Client();
        }
    }

    @Override
    public Client deleteClientById(UUID id) {
        Optional<Client> deletedClient = clientRepository.findById(id);
        if (deletedClient.isPresent()) {
            Client client = deletedClient.get();
            client.setDeletedStatus(DeletedStatus.DELETED);
            clientRepository.save(client);
            log.info("delete account where UUID - " + id);
            return deletedClient.get();
        } else {
            log.info("Account not found!");
            return new Client();
        }
    }

    @Override
    public List<Client> deleteClientsByStatus(ClientStatus status) {
        List<Client> clients = clientRepository.findClientsByStatus(status);
        clients.forEach(client -> client.setDeletedStatus(DeletedStatus.DELETED));
        log.info("deleting clients where Account Status - " + status);
        return clients;
    }

    @Override
    public Client editEmailById(UUID id, String eMail) {
        Optional<Client> clientFromDB = clientRepository.findById(id);
        if (clientFromDB.isPresent()) {
            Client client = clientFromDB.get();
            client.setEmail(eMail);
            clientRepository.save(client);
            log.info("Client's eMail successfully updated: " + id);
            return clientFromDB.get();
        } else {
            log.info("Client with client ID: " + id + " not found");
            return new Client();
        }
    }

    @Override
    public Client editPhoneById(UUID id, String phone) {
        Optional<Client> clientFromDB = clientRepository.findById(id);
        if (clientFromDB.isPresent()) {
            clientFromDB.get().setPhone(phone);
            clientRepository.save(clientFromDB.get());
            log.info("Client's phone successfully updated: " + id);
            return clientFromDB.get();
        } else {
            log.info("Client with client ID: " + id + " not found");
            return new Client();
        }
    }

    @Override
    public List<Client> findClientsByDeletedStatus(DeletedStatus deletedStatus) {
        return clientRepository.findClientsByDeletedStatus(deletedStatus);
    }

    @Override
    public Client restoreById(UUID id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            Client toRestore = client.get();
            toRestore.setDeletedStatus(DeletedStatus.ACTIVE);
            clientRepository.save(toRestore);
            log.info("client restored");
            return toRestore;
        } else {
            log.error("client not found");
            return new Client();
        }
    }

    @Override
    public List<Client> restoreAll() {
        List<Client> clients = clientRepository.findClientsByDeletedStatus(DeletedStatus.DELETED);
        clients.forEach(client -> client.setDeletedStatus(DeletedStatus.ACTIVE));
        clientRepository.saveAll(clients);
        log.info("all deleted Clients are restored!");
        return clients;
    }
}
