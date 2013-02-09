package com.amitgaur.cardprocessor.model;

import com.amitgaur.cardprocessor.Validatable;
import com.amitgaur.cardprocessor.Validator;

public class CreditCard implements Validatable {

    @Override
    public boolean accept(Validator v)  {
        return v.validate(this);

    }
}
