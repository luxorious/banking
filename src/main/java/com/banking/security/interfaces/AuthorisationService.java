package com.banking.security.interfaces;

import com.banking.entity.Client;
import com.banking.entity.Manager;
import com.banking.entity.entityenumerations.Role;
import com.banking.security.Authorisation;

import java.util.List;

public interface AuthorisationService {

    void save(Authorisation authorisation);

    void delete(Authorisation authorisation);

    void signIn(String login, String password, String repeatPassword);

    String findByLoginAndPassword(String login, String password);
    Authorisation findByLogin(String login);

    List<Authorisation> findAll();

    void changePassword(String login, String newPassword);

    Authorisation createClient(Client client);
    Authorisation createManager(Manager manager, Role role);
}
