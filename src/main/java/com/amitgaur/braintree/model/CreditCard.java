package com.amitgaur.braintree.model;

import com.amitgaur.braintree.model.com.amitgaur.braintree.services.CardValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: amitgaur
 * Date: 2/14/13
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreditCard {

    private final String cardNumber;
    private final String cardHolderName;
    private BigDecimal balance;
    private BigDecimal creditLimit;
    private boolean isValid;

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCard.class);

    public CreditCard(String cardNumber, String cardHolderName, BigDecimal creditLimit, CardValidator cardValidator) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.creditLimit = creditLimit;
        this.balance = BigDecimal.ZERO;
        this.isValid = cardValidator.validate(cardNumber);
    }

    public String getCardNumber(){
        return cardNumber;
    }
    public String getCardHolderName() {
        return  cardHolderName;
    }



    public BigDecimal getBalance() {
        return balance;
    }

    public synchronized  boolean charge(BigDecimal chargeAmount) {
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

    public synchronized  void credit(BigDecimal creditAmount){
        balance = balance.subtract(creditAmount);
    }


    public String toString() {

        StringBuilder builder = new StringBuilder();
       return builder.append("CardNum::").append(cardNumber).append(" CardHolder::").append(cardHolderName).append(" balance::").append(balance).append(" limit::").append(creditLimit).toString();
    }
}
