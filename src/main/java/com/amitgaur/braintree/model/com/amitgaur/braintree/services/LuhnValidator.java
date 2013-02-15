package com.amitgaur.braintree.model.com.amitgaur.braintree.services;

import com.google.common.base.Preconditions;

/**
 * Created with IntelliJ IDEA.
 * User: amitgaur
 * Date: 2/14/13
 * Time: 12:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class LuhnValidator implements CardValidator {

    @Override
    public boolean validate(String cardNumber) {
        Preconditions.checkArgument(cardNumber != null);
        boolean isSecondDigit = false;
        int sum = 0;
        for (int i = cardNumber.length()-1; i>=0;i--){
            int currentDigit = cardNumber.charAt(i)-'0';
            if (isSecondDigit){
                currentDigit*=2;
                if (currentDigit>=10){
                    currentDigit = (currentDigit%10)+1;
                }
            }
            sum+= currentDigit;
            isSecondDigit  = !isSecondDigit;

        }
        return (sum%10==0? true : false);
    }

}
