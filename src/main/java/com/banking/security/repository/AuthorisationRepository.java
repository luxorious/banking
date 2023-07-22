package com.banking.security.repository;

import com.banking.security.Authorisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for managing {@link com.banking.security.Authorisation} entities.
 *
 * @since 2023-07-19
 */
@Repository
public interface AuthorisationRepository extends JpaRepository<Authorisation, UUID> {

    /**
     * Retrieves an {@link com.banking.security.Authorisation} entity by its login (username).
     *
     * @param login The login (username) of the user.
     * @return An {@link java.util.Optional} containing the {@link com.banking.security.Authorisation} entity with the given login,
     * or an empty optional if no user is found.
     * @since 2023-07-19
     */
    Optional<Authorisation> findByLogin(String login);

    /**
     * Checks if an {@link com.banking.security.Authorisation} entity with the specified login (username) exists in the database.
     *
     * @param login The login (username) of the user.
     * @return {@code true} if an {@link com.banking.security.Authorisation} entity with the given login exists in the database,
     * {@code false} otherwise.
     * @since 2023-07-19
     */
    Boolean existsByLogin(String login);
}
