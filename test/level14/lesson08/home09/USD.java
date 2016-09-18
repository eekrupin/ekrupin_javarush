package com.javarush.test.level14.lesson08.home09;

/**
 * Created by Евгений on 09.08.2016.
 */
public class USD extends Money{

    public USD(double amount) {
        super(amount);
    }

    public String getCurrencyName() {
        return "USD";
    }

}
