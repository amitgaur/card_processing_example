package com.amitgaur.braintree.util;


import com.amitgaur.braintree.services.CreditCardManager;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigInteger;

public class LineSplitterTest {
    LineSplitter lineSplitter ;


    @Test
    public void testParsingBigInteger() {

        lineSplitter  = new LineSplitter(Mockito.mock(CreditCardManager.class));
        BigInteger value = lineSplitter.parseBigInteger("$1000");
        Assert.assertEquals(value, BigInteger.valueOf(1000));


    }
}
