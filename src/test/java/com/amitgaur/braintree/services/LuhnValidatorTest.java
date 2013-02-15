package com.amitgaur.braintree.services;

import com.amitgaur.braintree.model.com.amitgaur.braintree.services.CardValidator;
import com.amitgaur.braintree.model.com.amitgaur.braintree.services.LuhnValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: amitgaur
 * Date: 2/14/13
 * Time: 1:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class LuhnValidatorTest {

    CardValidator validator;
    @BeforeClass
    public void setup() {
        this.validator = new LuhnValidator();
    }

    @DataProvider(name="dp")
    public Object[][] getData() {
        return new Object[][]{{"49927398716", Boolean.TRUE},{"49927398717", Boolean.FALSE}, {"1234567812345678", Boolean.FALSE}, {"1234567812345670", Boolean.TRUE}};

    }

    @Test(dataProvider = "dp")
    public void test(String cardNumber, Boolean expectedValue){

        Assert.assertEquals(Boolean.valueOf(validator.validate(cardNumber)), expectedValue);
    }
}