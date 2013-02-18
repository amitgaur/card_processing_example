package com.amitgaur.braintree.util;


import com.amitgaur.braintree.model.ChargeType;
import com.amitgaur.braintree.services.CreditCardManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.regex.Pattern;

/**
 * Class that parses each line item and actions processing appropriately
 * supports
 * 1. Add Card
 * 2. Charge Card
 * 3. Credit Card
 *
 */
public class LineSplitter {

    private static final Pattern LINE_PATTERN = Pattern.compile("\\s");
    private final CreditCardManager creditCardManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(LineSplitter.class);


    public LineSplitter(CreditCardManager creditCardManager) {
        this.creditCardManager = creditCardManager;
    }

    public String[] split(String input) {
        return LINE_PATTERN.split(input);
    }

    public void action(String line) {

        String[] elements = split(line);
        if (elements.length < 3 || elements.length > 4) {

            LOGGER.warn("Invalid line, skipping parsing {}", line);
            return;
        }

        String action = elements[0];
        if (action.equalsIgnoreCase("ADD")) {
            creditCardManager.addCard(elements[1], elements[2], parseBigInteger(elements[3]));
        } else if (action.equalsIgnoreCase("CHARGE")) {
            creditCardManager.chargeCard(elements[1], parseBigInteger(elements[2]), ChargeType.CHARGE);
        } else if (action.equalsIgnoreCase("CREDIT")) {
            creditCardManager.chargeCard(elements[1], parseBigInteger(elements[2]), ChargeType.CREDIT);
        }

    }

    protected BigInteger parseBigInteger(String amountString) {

        return new BigInteger(amountString.substring(1));

    }
}
