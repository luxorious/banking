package com.banking.service.implementation.utility;

import com.banking.exception.BadListException;
import com.banking.service.interfaces.utility.ValidatorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class provides implementation for the {@link ValidatorService} interface.
 * It is responsible for validating entities and lists to ensure they meet the required criteria.
 *
 * @param <T> The type of entity or object to be validated.
 */
@Service
@Slf4j
public class ValidatorServiceImpl<T> implements ValidatorService<T> {

    @Value("${validatorComponent.error}")
    private String error;

    @Value("${validatorComponent.nullMessage}")
    private String nullMessage;

    @Value("${validatorComponent.validMessage}")
    private String validMessage;

    /**
     * Check if an entity is present in the Optional. If present, return the entity; otherwise, throw an EntityNotFoundException.
     *
     * @param t The Optional containing the entity to check.
     * @return The entity if present in the Optional.
     * @throws EntityNotFoundException If the entity is not present in the Optional.
     */
    @Override
    public T checkEntity(Optional<T> t) {
        return t.orElseThrow(() -> new EntityNotFoundException(error));
    }

    /**
     * Check if the given list is valid (not null). If the list is valid, return the list; otherwise, throw a BadListException.
     *
     * @param t The list to check for validity.
     * @return The valid list.
     * @throws BadListException If the list is null.
     */
    @Override
    public List<T> checkList(List<T> t) {
        if (t == null) {
            log.error(nullMessage);
            throw new BadListException(error);
        }
        log.info(validMessage + t);
        return t;
    }
}
