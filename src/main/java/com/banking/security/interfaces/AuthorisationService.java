package com.banking.security.interfaces;

import com.banking.entity.Client;
import com.banking.entity.Manager;
import com.banking.entity.entityenumerations.Role;
import com.banking.security.Authorisation;

import java.util.List;

/**
 * Interface for managing user authorizations and authentication.
 *
 * <p>This interface defines methods for saving and retrieving user authorizations, finding users by login and password,
 * changing passwords, and creating client and manager accounts.
 *
 * @since 2023-07-19
 */
public interface AuthorisationService {

    /**
     * Saves the given authorization entity.
     *
     * @param authorisation The authorization entity to be saved.
     * @since 2023-07-19
     */
    void save(Authorisation authorisation);

    /**
     * Finds a user by login and password.
     *
     * <p>This method searches for a user by the provided login and password. If the user is found and the password matches
     * the one stored in the database, the method returns a success message. Otherwise, it throws a {@link
     * com.banking.exception.BadLoginOrPasswordException} with an error message.
     *
     * @param login    The user's login (email).
     * @param password The user's password.
     * @return A success message if the login and password are correct.
     * @throws com.banking.exception.BadLoginOrPasswordException If the login or password is incorrect.
     * @since 2023-07-19
     */
    String findByLoginAndPassword(String login, String password);

    /**
     * Finds a user by login.
     *
     * <p>This method searches for a user by the provided login (email) and returns the corresponding {@link
     * com.banking.security.Authorisation} entity.
     *
     * @param login The user's login (email).
     * @return The {@link com.banking.security.Authorisation} entity with the given login.
     * @since 2023-07-19
     */
    Authorisation findByLogin(String login);

    /**
     * Retrieves a list of all user authorizations.
     *
     * @param login       The user's login (email).
     * @param newPassword The new password to be set.
     * @return A list of all user authorizations.
     * @since 2023-07-19
     * <p>
     * List<Authorisation> findAll();
     * <p>
     * /**
     * Changes the password for the user with the given login.
     *
     * <p>This method finds the user by login, updates their password with the new one, and saves the changes.
     * @since 2023-07-19
     */
    void changePassword(String login, String newPassword);

    /**
     * Creates a new client account and corresponding authorization.
     *
     * <p>This method creates a new {@link com.banking.security.Authorisation} entity for the given client and sends the
     * client an email containing their login (email) and randomly generated password. The client's role is set to {@link
     * com.banking.entity.entityenumerations.Role#USER}.
     *
     * @param client The client entity for whom the account is created.
     * @return The created {@link com.banking.security.Authorisation} entity.
     * @since 2023-07-19
     */
    Authorisation createClient(Client client);

    /**
     * Creates a new manager account and corresponding authorization.
     *
     * <p>This method creates a new {@link com.banking.security.Authorisation} entity for the given manager and sends the
     * manager an email containing their login (email) and randomly generated password. The manager's role is set to {@link
     * com.banking.entity.entityenumerations.Role#MANAGER} by default, unless the manager's role is {@link
     * com.banking.entity.entityenumerations.Role#ADMINISTRATOR}, in which case the role is set to the provided {@link
     * com.banking.entity.entityenumerations.Role}.
     *
     * @param manager The manager entity for whom the account is created.
     * @param role    The role to be assigned to the manager's account (default is {@link
     *                com.banking.entity.entityenumerations.Role#MANAGER}).
     * @return The created {@link com.banking.security.Authorisation} entity.
     * @since 2023-07-19
     */
    Authorisation createManager(Manager manager);

    /**
     * Retrieves a list of all user authorizations.
     *
     * <p>This method fetches all user authorizations stored in the system and returns them as a list of {@link
     * com.banking.security.Authorisation} entities.
     *
     * @return A list of all user authorizations.
     * @since 2023-07-19
     */
    List<Authorisation> findAll();
}
