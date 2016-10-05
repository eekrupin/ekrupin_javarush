package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.OurHashMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Евгений on 05.10.2016.
 */
public class Solution {

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> longs = new HashSet<>();
        for (String string : strings) {
            longs.add(shortener.getId(string));
        }
        return longs;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> strings = new HashSet<>();
        for (Long key : keys) {
            strings.add(shortener.getString(key));
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> strings = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date dateBegin = new Date();
        Set<Long> longs = getIds(shortener, strings);
        Date dateEnd = new Date();
        System.out.println(dateEnd.getTime()-dateBegin.getTime());

        dateBegin = new Date();
        Set<String> stringsFromSet = getStrings(shortener, longs);
        dateEnd = new Date();
        System.out.println(dateEnd.getTime()-dateBegin.getTime());

        if (strings.containsAll(stringsFromSet)) Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");

    }

}
