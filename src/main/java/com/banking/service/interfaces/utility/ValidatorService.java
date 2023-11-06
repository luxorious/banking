package com.banking.service.interfaces.utility;

import java.util.List;
import java.util.Optional;

/**
 * This interface defines the contract for validating entities and lists of entities.
 *
 * @param <T> The type of entity to be validated.
 */
public interface ValidatorService<T> {
    /**
     * Validates whether an entity is present in the given Optional, or returns a default entity if the Optional is empty.
     *
     * @param t The Optional containing the entity.
     * @return The entity if present, otherwise a default entity.
     */
    T checkEntity(Optional<T> t);

    /**
     * Validates a list of entities and returns the list.
     *
     * @param t The list of entities to be validated.
     * @return The validated list of entities.
     */
    List<T> checkList(List<T> t);
}
