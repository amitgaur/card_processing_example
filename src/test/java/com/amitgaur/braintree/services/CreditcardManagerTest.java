package com.amitgaur.braintree.services;

import com.amitgaur.braintree.model.ChargeType;
import com.amitgaur.braintree.model.CreditCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: amitgaur
 * Date: 2/15/13
 * Time: 9:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreditCardManagerTest {

    private CreditCardManager cardManager;
    protected static final Logger LOG = LoggerFactory.getLogger(CreditCardManagerTest.class);


    @BeforeClass
    public void setUp() {
        cardManager = new CreditCardManagerImpl(new CreditCardFactory(new LuhnValidator()));

    }


    @Test
    public void testBasicCardOperations() {

        cardManager.addCard("Tom", "4111111111111111", BigInteger.valueOf(1000));
        cardManager.addCard("Lisa", "5454545454545454", BigInteger.valueOf(3000));
        cardManager.addCard("Quincy", "1234567890123456", BigInteger.valueOf(2000));
        cardManager.addCard("Amit", "4796141582796639", BigInteger.valueOf(100));

        Assert.assertTrue(cardManager.getCards().size() == 4);

        cardManager.chargeCard("Tom", BigInteger.valueOf(500), ChargeType.CHARGE);
        CreditCard tomCard = cardManager.getCard("Tom");
        Assert.assertTrue(tomCard.getBalance().equals(BigInteger.valueOf(500)));
        cardManager.chargeCard("Tom", BigInteger.valueOf(800), ChargeType.CHARGE);
        Assert.assertTrue(tomCard.getBalance().equals(BigInteger.valueOf(500)));


        CreditCard lisaCard = cardManager.getCard("Lisa");
        cardManager.chargeCard("Lisa", BigInteger.valueOf(7), ChargeType.CHARGE);
        Assert.assertTrue(lisaCard.getBalance().equals(BigInteger.valueOf(7)));
        cardManager.chargeCard("Lisa", BigInteger.valueOf(100), ChargeType.CREDIT);
        Assert.assertTrue(lisaCard.getBalance().equals(BigInteger.valueOf(-93)));



        CreditCard qCard = cardManager.getCard("Quincy");
        Assert.assertFalse(qCard.isValid());
        cardManager.chargeCard("Quincy", BigInteger.valueOf(100), ChargeType.CHARGE);
        LOG.info(qCard.prettyPrint());

        CreditCard amitCard = cardManager.getCard("Amit");
        Assert.assertTrue(amitCard.isValid());
        cardManager.chargeCard("Amit", BigInteger.valueOf(100), ChargeType.CHARGE);
        Assert.assertTrue(amitCard.getBalance().equals(BigInteger.valueOf(100)));

        LOG.info(amitCard.prettyPrint());



    }
}
