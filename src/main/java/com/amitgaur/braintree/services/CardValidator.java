package com.amitgaur.braintree.services;

/**
 * Card Validator
 */
public interface CardValidator {

    /**
     * Validate a cardNumber
     *
     * @param cardNumber
     * @return true if validation succeeds
     */
    public boolean validate(String cardNumber);
}
