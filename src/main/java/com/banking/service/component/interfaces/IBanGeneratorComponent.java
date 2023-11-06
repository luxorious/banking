package com.banking.service.component.interfaces;

/**
 * This interface defines the contract for an IBAN (International Bank Account Number) generator component.
 * Implementing classes are responsible for generating IBANs.
 */
public interface IBanGeneratorComponent {

    /**
     * Generate a new IBAN (International Bank Account Number).
     *
     * @return The generated IBAN.
     */
    String generate();
}
