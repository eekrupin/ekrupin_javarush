package com.javarush.test.level26.lesson15.big01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Евгений on 13.09.2016.
 */
public class CurrencyManipulatorFactory {

    private static HashMap<String, CurrencyManipulator> map = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        if (!map.containsKey(currencyCode)) map.put(currencyCode, new CurrencyManipulator(currencyCode));

        return map.get(currencyCode);
    }

    private CurrencyManipulatorFactory() {
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        ArrayList<CurrencyManipulator> manipulators= new ArrayList<>();
        for (Map.Entry<String, CurrencyManipulator> entry : map.entrySet()) {
            manipulators.add(entry.getValue());
        }
        return manipulators;
    }

    public static boolean hasMoney(){
        boolean hasMoney = false;
        for (Map.Entry<String, CurrencyManipulator> entry : map.entrySet()) {
            if (entry.getValue().getTotalAmount()>0) return true;
        }
        return hasMoney;
    }

}
