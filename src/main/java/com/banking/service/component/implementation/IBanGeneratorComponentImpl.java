package com.banking.service.component.implementation;

import com.banking.service.component.interfaces.IBanGeneratorComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Implementation of the IBanGeneratorComponent interface that generates International Bank Account Numbers (IBANs)
 * based on the provided configuration settings.
 */
@Slf4j
@Component
public class IBanGeneratorComponentImpl implements IBanGeneratorComponent {

    @Value("${iBan.countryCode}")
    private String countryCode;

    @Value("${iBan.controlNumber}")
    private int controlNumber;

    @Value("${iBan.bankCode}")
    private int bankCode;

    @Value("${iBan.additionalSymbols}")
    private String additionalSymbols;

    /**
     * Generates an International Bank Account Number (IBAN) based on the configured settings.
     *
     * @return The generated IBAN.
     */
    @Override
    public String generate() {
        StringBuilder iBanNumber = new StringBuilder(countryCode + controlNumber + bankCode + additionalSymbols);
        for (int i = 0; i < 14; i++) {
            iBanNumber.append(new Random().nextInt(10));//NOSONAR
        }
        return String.valueOf(iBanNumber);
    }
}
