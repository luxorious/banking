package com.banking.security.implementations;

import com.banking.entity.Client;
import com.banking.entity.Manager;
import com.banking.entity.entityenumerations.Role;
import com.banking.exception.BadLoginOrPasswordException;
import com.banking.security.Authorisation;
import com.banking.security.interfaces.AuthorisationService;
import com.banking.security.repository.AuthorisationRepository;
import com.banking.service.interfaces.utility.ValidatorService;
import com.banking.service.mailservice.MailSender;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorisationServiceImpl implements AuthorisationService {

    private final AuthorisationRepository authorisationRepository;
    private final ValidatorService<Authorisation> validatorService;
    private final MailSender mailSender;

    @Value("${postService}")
    private String postService;
    @Value("${registration.subject}")
    private String subject;
    @Value("${registration.text}")
    private String text;
    @Value("${firstAdminMail}")
    private String firstAdminMail;
    @Value("${firstAdminPassword}")
    private String firstAdminPassword;

    @PostConstruct
    public void init() {
        boolean isAdminExist = authorisationRepository.existsByLogin(firstAdminMail);
        if (isAdminExist) {
            log.info("already added");
        } else {
            Authorisation auth = new Authorisation();
            auth.setLogin(firstAdminMail);
            auth.setPassword(firstAdminPassword);
            auth.setRole(Role.ADMINISTRATOR);
            save(auth);
            mailSender.send(firstAdminMail, text, subject);
            log.info("first admin created");
        }
    }

    @Override
    public void save(Authorisation authorisation) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncoded = encoder.encode(authorisation.getPassword());
        authorisation.setPassword(passwordEncoded);
        authorisationRepository.save(authorisation);
    }

    @Override
    public void delete(Authorisation authorisation) {

    }

    @Override
    public void signIn(String login, String password, String repeatPassword) {

    }

    @Override
    public String findByLoginAndPassword(String login, String password) {
        Authorisation user = validatorService.checkEntity(authorisationRepository.findByLogin(login));
        if (Boolean.TRUE.equals(checkPassword(password, user.getPassword()))) {
            throw new BadLoginOrPasswordException("wrong e-mail");
        } else {
            return "user found";
        }
    }

    @Override
    public Authorisation findByLogin(String login) {
        return validatorService.checkEntity(authorisationRepository.findByLogin(login));
    }

    @Override
    public List<Authorisation> findAll() {
        return authorisationRepository.findAll();
    }

    @Override
    public void changePassword(String login, String newPassword) {
        Authorisation auth = findByLogin(login);
        auth.setPassword(newPassword);
        save(auth);
    }

    @Override
    public Authorisation createClient(Client client) {
        log.info("Client id - " + client.getId());
        Authorisation auth = new Authorisation();
        auth.setUserId(client.getId());
        log.info("Client id - " + auth.getUserId());
        auth.setLogin(client.getEmail());
        auth.setPassword(generatePassword());
        auth.setRole(Role.USER);
        String message = String.format(text, auth.getLogin(), auth.getPassword());
        mailSender.send(auth.getLogin(), message, subject);
        return authorisationRepository.save(auth);
    }

    @Override
    public Authorisation createManager(Manager manager, Role role) {
        Authorisation auth = new Authorisation();
        auth.setUserId(manager.getId());
        log.info("Manager id - " + auth.getUserId());
        log.info("Manager id - " + manager.getId());
        auth.setLogin(manager.getFirstName() + "_" + manager.getLastName() + postService);
        auth.setPassword(generatePassword());
        auth.setRole(Role.MANAGER);
        if (manager.getRole() == Role.ADMINISTRATOR) {
            auth.setRole(role);
        } else {
            auth.setRole(Role.MANAGER);
        }
        String message = String.format(text, auth.getLogin(), auth.getPassword());
        mailSender.send(auth.getLogin(), message, subject);
        return authorisationRepository.save(auth);
    }

    private String generatePassword() {
        return String.valueOf(UUID.randomUUID());
    }

    private Boolean checkPassword(String password, String userPasswordDb) {
        PasswordEncoder pe = new BCryptPasswordEncoder();
        if (pe.matches(password, userPasswordDb)) {
            return true;
        }
        throw new BadLoginOrPasswordException("wrong password!");
    }
}
