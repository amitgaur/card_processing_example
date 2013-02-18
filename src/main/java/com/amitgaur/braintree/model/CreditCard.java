package com.amitgaur.braintree.model;


import java.math.BigInteger;

/**
 * Basic Representation of a Credit Card Object
 *
 */
public abstract class CreditCard {

    protected final String cardNumber;
    protected final String cardHolderName;
    protected BigInteger balance;
    protected BigInteger creditLimit;
    protected boolean isValid;


    public CreditCard(String cardHolderName, String cardNumber, BigInteger creditLimit) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.creditLimit = creditLimit;
        this.balance = BigInteger.ZERO;
    }

    public String getCardNumber(){
        return cardNumber;
    }
    public String getCardHolderName() {
        return  cardHolderName;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public boolean isValid() {
        return isValid;
    }

    /**
     * @param chargeAmount Debit the amount from the card
     * @return true if the balance does not cross the limit of the card
     *
     *
     */
    public abstract   boolean charge(BigInteger chargeAmount);


    /**
     * Credit an amount the card
     * @param creditAmount
     */
    public abstract  void credit(BigInteger creditAmount);

    public abstract String prettyPrint();


    public String toString() {

        StringBuilder builder = new StringBuilder();
        return builder.append("CardNum::").append(cardNumber).append(" CardHolder::").append(cardHolderName).append(" balance::").append(balance).append(" limit::").append(creditLimit).toString();
    }
}
