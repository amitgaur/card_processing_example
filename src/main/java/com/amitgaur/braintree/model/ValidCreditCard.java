package com.amitgaur.braintree.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * Credit Card that passes Validation Algorithm
 * Supports credit and debit functions
 */
public class ValidCreditCard extends CreditCard   {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidCreditCard.class);

    public ValidCreditCard(String cardHolderName, String cardNumber, BigInteger balance) {
        super(cardHolderName, cardNumber,balance);
        this.isValid = true;
    }

    @Override
    public synchronized  boolean debit(BigInteger chargeAmount) {
        boolean success = true;
        if (balance.add(chargeAmount).compareTo(creditLimit)>0) {
            LOGGER.info("Balance has fallen below credit limit for request ::" + chargeAmount + " cardDetails::" + this.toString());
            success = false;
        }
        else {
            balance = balance.add(chargeAmount);
        }
        return success;
    }

    @Override
    public synchronized  void credit(BigInteger creditAmount){
        balance = balance.subtract(creditAmount);
    }

    @Override
    public String prettyPrint() {
        StringBuilder builder = new StringBuilder();
        return builder.append(cardHolderName).append(": ").append("$").append(balance).toString();
    }

}
