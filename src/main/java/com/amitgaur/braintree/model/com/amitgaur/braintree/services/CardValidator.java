package com.amitgaur.braintree.services;

/**
 * Created with IntelliJ IDEA.
 * User: amitgaur
 * Date: 2/14/13
 * Time: 12:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CardValidator {

    public boolean validate(String cardNumber);
}
