package com.banking.service.implementation.utility;

import com.banking.service.interfaces.utility.GetEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetEntityImpl<T> implements GetEntity<T> {

    @Override
    public T getEntity(Optional<T> t) {
        return t.orElseThrow(() -> new EntityNotFoundException("entity not found"));
    }
}
