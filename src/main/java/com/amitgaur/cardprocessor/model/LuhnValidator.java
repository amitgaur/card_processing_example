package com.amitgaur.cardprocessor.model;

import com.amitgaur.cardprocessor.Validatable;
import com.amitgaur.cardprocessor.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: amitgaur
 * Date: 2/9/13
 * Time: 12:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class LuhnValidator implements Validator<CreditCard>{
    @Override
    public boolean validate(CreditCard creditCard) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
