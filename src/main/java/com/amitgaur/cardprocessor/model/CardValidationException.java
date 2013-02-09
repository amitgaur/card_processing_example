package com.amitgaur.cardprocessor.model;

/**
 * Created with IntelliJ IDEA.
 * User: amitgaur
 * Date: 2/9/13
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class CardValidationException extends Exception {
    public CardValidationException(String message){
        super(message);
    }
}
