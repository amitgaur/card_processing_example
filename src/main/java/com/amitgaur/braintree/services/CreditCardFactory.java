package com.amitgaur.braintree.services;

import com.amitgaur.braintree.model.CreditCard;
import com.amitgaur.braintree.model.InvalidCreditCard;
import com.amitgaur.braintree.model.ValidCreditCard;

import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: amitgaur
 * Date: 2/15/13
 * Time: 8:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreditCardFactory {

    private CardValidator cardValidator;

    public CreditCardFactory(CardValidator cardValidator) {
        this.cardValidator = cardValidator;
    }

    public CreditCard getCard(String cardHolderName, String cardNumber, BigInteger balance) {
        if (cardValidator.validate(cardNumber)) {
            return new ValidCreditCard(cardHolderName, cardNumber, balance);
        } else
            return
                    new InvalidCreditCard(cardHolderName, cardNumber, balance);


    }
}
