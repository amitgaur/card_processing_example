package com.amitgaur.braintree.services;

import com.amitgaur.braintree.model.ChargeType;
import com.amitgaur.braintree.model.CreditCard;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: amitgaur
 * Date: 2/15/13
 * Time: 8:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreditCardManagerImpl implements CreditCardManager {

    private final Map<String, CreditCard> cardRegistry;
    private final CreditCardFactory cardFactory;


    public CreditCardManagerImpl(CreditCardFactory cardFactory) {
        this.cardRegistry = new HashMap<String, CreditCard>();
        this.cardFactory = cardFactory;
    }

    @Override
    public void addCard(String name, String cardNumber, BigInteger limit) {
        cardRegistry.put(name, cardFactory.getCard(name, cardNumber, limit));
    }

    @Override
    public void chargeCard(String cardHolder, BigInteger amount, ChargeType chargeType) {
        CreditCard card = cardRegistry.get(cardHolder);
        if (card != null && card.isValid()) {
            switch (chargeType) {
                case CHARGE:
                    card.charge(amount);
                    break;
                case CREDIT:
                    card.credit(amount);
                    break;
            }

        }
    }

    @Override
    public Collection<CreditCard> getCards() {
        return cardRegistry.values();

    }

    @Override
    public CreditCard getCard(String name) {
        return cardRegistry.get(name);

    }
}
