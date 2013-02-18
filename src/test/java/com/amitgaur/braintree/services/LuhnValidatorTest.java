package com.amitgaur.braintree.services;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LuhnValidatorTest {

    CardValidator validator;

    @BeforeClass
    public void setup() {
        this.validator = new LuhnValidator();
    }

    @DataProvider(name = "dp")
    public Object[][] getData() {
        return new Object[][]{{"49927398716", Boolean.TRUE}, {"49927398717", Boolean.FALSE}, {"1234567812345678", Boolean.FALSE}, {"1234567812345670", Boolean.TRUE}};

    }

    @Test(dataProvider = "dp")
    public void test(String cardNumber, Boolean expectedValue) {

        Assert.assertEquals(Boolean.valueOf(validator.validate(cardNumber)), expectedValue);
    }
}
