package com.amitgaur.braintree.services;

import com.google.common.base.Preconditions;

/**
 * Implementation of mod 10 Luhn Validation Algorithm for Credit Card Numbers
 * http://en.wikipedia.org/wiki/Luhn_algorithm
 */
public class LuhnValidator implements CardValidator {

    @Override
    public boolean validate(String cardNumber) {
        Preconditions.checkArgument(cardNumber != null);
        boolean isSecondDigit = false;
        int sum = 0;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int currentDigit = cardNumber.charAt(i) - '0';
            if (isSecondDigit) {
                currentDigit *= 2;
                if (currentDigit >= 10) {
                    currentDigit = (currentDigit % 10) + 1;
                }
            }
            sum += currentDigit;
            isSecondDigit = !isSecondDigit;

        }
        return (sum % 10 == 0 ? true : false);
    }

}
