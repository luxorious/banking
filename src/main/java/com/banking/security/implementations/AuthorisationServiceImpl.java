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

/**
 * The {@code AuthorisationServiceImpl} class is responsible for handling user authentication and authorization logic.
 * It implements the {@link AuthorisationService} interface and provides methods to manage user credentials and roles.
 *
 * <p>Methods in this class are used to save user credentials, sign in users, find users by login, change passwords,
 * create clients, and create managers with appropriate roles. It also provides a method to find all users in the system.
 *
 * <p>Upon initialization, this class checks if the first admin user exists in the system. If not, it creates the first
 * admin user with the provided email and password and sends a confirmation email using the {@link MailSender} service.
 *
 * <p>Before saving user credentials, the passwords are encrypted using {@link BCryptPasswordEncoder}.
 * The class also utilizes the {@link ValidatorService} to check if entities retrieved from the repository are not null,
 * throwing exceptions if necessary.
 *
 * @author Volodymyr Dymka
 * @version 1.0
 * @since 2023-07-19
 */
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

    /**
     * The {@code init()} method is annotated with {@link PostConstruct}, which indicates that it will be executed
     * after the bean is initialized by the Spring container.
     *
     * <p>This method is used to check if the first admin user exists in the system. If the admin user does not exist,
     * it creates the first admin user with the provided email and password, sets the role as {@link Role#ADMINISTRATOR},
     * saves the user credentials using the {@code save()} method, and sends a confirmation email using the {@link MailSender} service.
     *
     * <p>This method is called automatically during bean initialization, and it ensures that the first admin user is present
     * in the system when the application starts.
     *
     * @throws RuntimeException if there is an error while saving the user credentials or sending the confirmation email.
     * @since 2023-07-19
     */
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

    /**
     * Saves the given {@link Authorisation} object after encoding the password using the BCryptPasswordEncoder.
     *
     * <p>This method encodes the password provided in the {@link Authorisation} object using the BCryptPasswordEncoder,
     * and then saves the modified object in the database using the {@link AuthorisationRepository}.
     *
     * @param authorisation The {@link Authorisation} object containing user credentials to be saved.
     * @throws RuntimeException if there is an error while encoding the password or saving the {@link Authorisation} object.
     * @since 2023-07-19
     */
    @Override
    public void save(Authorisation authorisation) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncoded = encoder.encode(authorisation.getPassword());
        authorisation.setPassword(passwordEncoded);
        authorisationRepository.save(authorisation);
    }

    /**
     * Finds a user in the database with the given login and verifies the provided password.
     *
     * <p>This method queries the database using the {@link AuthorisationRepository} to find a user with the provided login.
     * If a user with the given login is found, the method checks the provided password against the stored hashed password
     * using the {@link #checkPassword(String, String)} method. If the provided password matches the stored hashed password,
     * it indicates successful login, and the method returns "user found". Otherwise, it throws a
     * {@link BadLoginOrPasswordException} indicating incorrect login credentials.
     *
     * @param login    The login of the user to be found.
     * @param password The password to be verified.
     * @return A message indicating successful login if the provided credentials are correct.
     * @throws BadLoginOrPasswordException if the provided login or password is incorrect.
     * @since 2023-07-19
     */
    @Override
    public String findByLoginAndPassword(String login, String password) {
        Authorisation user = validatorService.checkEntity(authorisationRepository.findByLogin(login));
        if (Boolean.TRUE.equals(checkPassword(password, user.getPassword()))) {
            throw new BadLoginOrPasswordException("wrong e-mail");
        } else {
            return "user found";
        }
    }

    /**
     * Finds an {@link Authorisation} entity in the database based on the provided login.
     *
     * <p>This method queries the database using the {@link AuthorisationRepository} to find an {@link Authorisation} entity
     * with the provided login. If an {@link Authorisation} entity with the given login is found, the method returns the
     * entity. If no entity is found or the entity is invalid (e.g., null), it throws a {@link BadLoginOrPasswordException}
     * indicating that the login does not exist or is incorrect.
     *
     * @param login The login of the user to be found.
     * @return The {@link Authorisation} entity with the provided login if it exists and is valid.
     * @throws BadLoginOrPasswordException if the provided login does not exist or is incorrect.
     * @since 2023-07-19
     */
    @Override
    public Authorisation findByLogin(String login) {
        return validatorService.checkEntity(authorisationRepository.findByLogin(login));
    }

    /**
     * Retrieves all {@link Authorisation} entities from the database.
     *
     * <p>This method queries the database using the {@link AuthorisationRepository} to fetch all {@link Authorisation}
     * entities. It returns a list containing all the fetched entities. If no entities are found, the method returns an
     * empty list.
     *
     * @return A list of all {@link Authorisation} entities present in the database, or an empty list if no entities are found.
     * @since 2023-07-19
     */
    @Override
    public List<Authorisation> findAll() {
        return authorisationRepository.findAll();
    }

    /**
     * Changes the password for the user with the specified login.
     *
     * <p>This method updates the password of the user with the given login in the database. It retrieves the user using the
     * {@link #findByLogin(String)} method, sets the new password, and then saves the updated {@link Authorisation} entity
     * to the database using the {@link #save(Authorisation)} method.
     *
     * @param login       The login of the user whose password needs to be changed.
     * @param newPassword The new password to set for the user.
     * @since 2023-07-19
     */
    @Override
    public void changePassword(String login, String newPassword) {
        Authorisation auth = findByLogin(login);
        auth.setPassword(newPassword);
        save(auth);
    }

    /**
     * Creates a new user (client) and generates their authorization details.
     *
     * <p>This method creates a new {@link Authorisation} entity for the given client. It sets the client's unique ID as the
     * user ID, uses the client's email as the login, generates a random password, sets the role as USER, and then saves the
     * new {@link Authorisation} entity to the database using the {@link #save(Authorisation)} method. Additionally, it sends
     * an email to the client with their login credentials using the {@link com.banking.service.mailservice.MailSender}
     * service.
     *
     * @param client The client for whom to create the user (client) and authorization details.
     * @return The newly created {@link Authorisation} entity containing the client's authorization details.
     * @since 2023-07-19
     */
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

    /**
     * Creates a new user (manager) and generates their authorization details.
     *
     * <p>This method creates a new {@link Authorisation} entity for the given manager. It sets the manager's unique ID as the
     * user ID, generates a login using the manager's first name, last name, and the {@code postService} value, generates a
     * random password, sets the role as MANAGER, and then saves the new {@link Authorisation} entity to the database using
     * the {@link #save(Authorisation)} method. If the manager's role is ADMINISTRATOR, the method will set the role as the
     * provided role parameter, otherwise, it will keep the role as MANAGER. Additionally, it sends an email to the manager
     * with their login credentials using the {@link com.banking.service.mailservice.MailSender} service.
     *
     * @param manager The manager for whom to create the user (manager) and authorization details.
     * @return The newly created {@link Authorisation} entity containing the manager's authorization details.
     * @since 2023-07-19
     */
    @Override
    public Authorisation createManager(Manager manager) {
        Authorisation auth = new Authorisation();
        auth.setUserId(manager.getId());
        log.info("Manager id - " + auth.getUserId());
        log.info("Manager id - " + manager.getId());
        auth.setLogin(manager.getFirstName() + "_" + manager.getLastName() + postService);
        auth.setPassword(generatePassword());
        auth.setRole(Role.MANAGER);
        String message = String.format(text, auth.getLogin(), auth.getPassword());
        mailSender.send(auth.getLogin(), message, subject);
        return authorisationRepository.save(auth);
    }

    /**
     * Generates a random password for new user accounts.
     *
     * <p>This method generates a random password using the {@link UUID#randomUUID()} method, which creates a unique
     * identifier (UUID) in the form of a string. The generated UUID is then used as the password for the new user account.
     *
     * @return A randomly generated password in the form of a UUID string.
     * @since 2023-07-19
     */
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
