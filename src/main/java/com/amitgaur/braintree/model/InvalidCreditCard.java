package com.amitgaur.braintree.model;

import java.math.BigInteger;

/**
 * Credit Card that does not pass validation function
 *
 */
public class InvalidCreditCard extends CreditCard {
    public InvalidCreditCard(String cardHolderName, String cardNumber, BigInteger balance) {
        super(cardHolderName,cardNumber,balance);
        this.isValid = false;
    }

    @Override
    public boolean charge(BigInteger chargeAmount) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void credit(BigInteger creditAmount) {
        throw new UnsupportedOperationException();
    }


    @Override
    public String prettyPrint() {
        StringBuilder builder = new StringBuilder();
        return builder.append(cardHolderName).append(": ").append("error").toString();
    }
}
