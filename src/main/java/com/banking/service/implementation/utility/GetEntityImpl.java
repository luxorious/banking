package com.banking.service.implementation.utility;

import com.banking.service.interfaces.utility.GetEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * An implementation of the {@link GetEntity} interface for retrieving entities from Optional objects.
 *
 * @param <T> The type of entity.
 */
@Component
public class GetEntityImpl<T> implements GetEntity<T> {
    /**
     * Get the entity from the Optional object or throw an EntityNotFoundException if not found.
     *
     * @param t The Optional object containing the entity.
     * @return The entity if present in the Optional, otherwise throw EntityNotFoundException.
     * @throws EntityNotFoundException If the entity is not present in the Optional.
     */
    @Override
    public T getEntity(Optional<T> t) {
        return t.orElseThrow(() -> new EntityNotFoundException("entity not found"));
    }
}
