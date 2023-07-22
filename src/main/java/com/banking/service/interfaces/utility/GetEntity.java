package com.banking.service.interfaces.utility;

import java.util.Optional;

/**
 * This interface defines the contract for retrieving an entity from an Optional.
 *
 * @param <R> The type of the entity to be retrieved.
 */
public interface GetEntity<R> {
    /**
     * Retrieves the entity from the given Optional, or returns null if the Optional is empty.
     *
     * @param r The Optional containing the entity.
     * @return The entity if present, otherwise null.
     */
    R getEntity(Optional<R> r);
}
