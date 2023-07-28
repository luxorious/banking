package com.banking.service.dbfiller;

import com.banking.entity.Bank;
import com.banking.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Component class for filling the Bank entity in the database.
 */
@Component
@RequiredArgsConstructor
public class BankFiller {

    private final BankRepository bankRepository;

    @Value("${bankUuid}")
    private UUID id;

    @Value("${bankIban}")
    private String iban;

    @Value("${bankCapital}")
    private String balance;

    /**
     * Fills the Bank entity in the database with the provided data.
     */
    public void fillBank() {
        BigDecimal capital = BigDecimal.valueOf(Long.parseLong(this.balance));
        Bank bank = new Bank(id, iban, capital);
        bankRepository.save(bank);
    }
}
