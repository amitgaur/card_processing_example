package com.amitgaur.braintree.services;

/**
 * Created with IntelliJ IDEA.
 * User: amitgaur
 * Date: 2/14/13
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CreditCardManager {

    public void addCard(String name, String cardNumber, BigDecimal balance);

    public void chargeCard(String cardHolder, BigDecimal amount, ChargeType chargeType);


}
