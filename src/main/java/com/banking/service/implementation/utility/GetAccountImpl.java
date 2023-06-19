package com.banking.service.implementation.utility;

import com.banking.entity.Account;
import com.banking.service.interfaces.utility.GetEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class GetAccountImpl implements GetEntity<Account> {

    @Override
    public Account getEntity(Optional<Account> account) {
        return account.orElseThrow(() -> new EntityNotFoundException("entity not found"));
    }
}
