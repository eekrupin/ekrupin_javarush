package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Евгений on 13.09.2016.
 */
public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new TreeMap<>(Collections.reverseOrder());

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count){
        if (!denominations.containsKey(denomination)) denominations.put(denomination, 0);
        denominations.put(denomination, denominations.get(denomination)+count);
    }

    public int getTotalAmount(){
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            sum+=entry.getKey()*entry.getValue();
        }
        return sum;
    }

    public boolean isAmountAvailable(int expectedAmount){
        return expectedAmount<=getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        TreeMap<Integer, Integer> withdrawMap = new TreeMap<>(Collections.reverseOrder());
        TreeMap<Integer, Integer> prevWithdrawMap = new TreeMap<>(Collections.reverseOrder());
        HashMap<Integer, Integer> usedNom = new HashMap<>();
        HashMap<Integer, Integer> tempUsedNom = new HashMap<>();

        int getSum = 0;
        boolean running = true;

        while (running){
            for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
                int nominal = entry.getKey();
                for (int i = 0; i <entry.getValue() ; i++) {
                    if (getSum+nominal<=expectedAmount) {
                        if (tempUsedNom.containsKey(nominal) && tempUsedNom.get(nominal)>0){
                            tempUsedNom.put(nominal, tempUsedNom.get(nominal)-1);
                            continue;
                        }
                        getSum+=nominal;
                        if (!withdrawMap.containsKey(nominal)) withdrawMap.put(nominal, 0);
                        withdrawMap.put(nominal, withdrawMap.get(nominal) + 1);
                    }
                    else break;
                    running = getSum!=expectedAmount;
                    if (!running) break;
                }
                if (!running) break;
            }
            running = running && getSum!=expectedAmount && withdrawMap.size()>0 && !withdrawMap.equals(prevWithdrawMap);
            if (running){
                getSum = 0;
                int nom = withdrawMap.firstEntry().getKey();
                if (!usedNom.containsKey(nom)) usedNom.put(nom, 0);
                usedNom.put(nom, usedNom.get(nom) + 1);
                tempUsedNom.clear();
                tempUsedNom.putAll(usedNom);
                prevWithdrawMap.clear();
                prevWithdrawMap.putAll(withdrawMap);
                withdrawMap.clear();
            }
        }

        if (getSum!=expectedAmount) throw new NotEnoughMoneyException();

        for (Map.Entry<Integer, Integer> entry : withdrawMap.entrySet()) {
            denominations.put(entry.getKey(), denominations.get(entry.getKey())-entry.getValue());
        }

        return withdrawMap;
    }

}
