package com.banking.dbfiller;

import com.banking.entity.Bank;
import com.banking.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BankFiller {

    private final BankRepository bankRepository;

    @Value("${bankUuid}")
    private UUID id;

    @Value("${bankIban}")
    private String iban;
    private final BigDecimal balance = BigDecimal.valueOf(500000.00);

    public void fillBank(){
        Bank bank = new Bank(id,iban, balance);
        bankRepository.save(bank);
    }
}
