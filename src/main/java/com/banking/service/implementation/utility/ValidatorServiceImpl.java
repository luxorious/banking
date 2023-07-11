package com.banking.service.implementation.utility;

import com.banking.exception.BadListException;
import com.banking.service.interfaces.utility.ValidatorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ValidatorServiceImpl<T> implements ValidatorService<T> {

    @Value("${validatorComponent.error}")
    private String error;

    @Value("${validatorComponent.nullMessage}")
    private String nullMessage;

    @Value("${validatorComponent.validMessage}")
    private String validMessage;

    @Override
    public T checkEntity(Optional<T> t) {
        return t.orElseThrow(() -> new EntityNotFoundException(error));
    }

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
