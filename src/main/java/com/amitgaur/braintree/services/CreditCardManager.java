package com.amitgaur.braintree.services;

import com.amitgaur.braintree.model.ChargeType;
import com.amitgaur.braintree.model.CreditCard;

import java.math.BigInteger;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: amitgaur
 * Date: 2/14/13
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CreditCardManager {

    /**
     * Add a card to the card repository.If card does not pass validation algorithm, it is still added to the repo
     * an invalid state
     *
     * @param name       Card Holder Name
     * @param cardNumber Card Number (Numbers as string)
     * @param limit      (Integer Limit)
     * @see CreditCard
     */
    public void addCard(String name, String cardNumber, BigInteger limit);

    /**
     * Charge an amount to a card
     * @param cardHolder
     * @param amount
     * @param chargeType     supported charge types
     */
    public void chargeCard(String cardHolder, BigInteger amount, ChargeType chargeType);


    /**
     * Get a list of all cards in the repo
     * @return
     */
    public Collection<CreditCard> getCards();

    /**
     * Get card for a particular cardHolder
     * @param name
     * @return
     */
    public CreditCard getCard(String name);


}
