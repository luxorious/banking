package com.banking.service.implementation;

import com.banking.entity.Client;
import com.banking.entity.entityEnumerations.ClientStatus;
import com.banking.repository.ClientRepository;
import com.banking.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
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
    public List<Client> findClientByStatus(ClientStatus status) {
        return clientRepository.findClientByStatus(status);
    }
}
