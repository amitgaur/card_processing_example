package com.amitgaur.cardprocessor;

/**
 * Created with IntelliJ IDEA.
 * User: amitgaur
 * Date: 2/9/13
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Validator<T extends Validatable> {
    public boolean validate(T validatable);
}
