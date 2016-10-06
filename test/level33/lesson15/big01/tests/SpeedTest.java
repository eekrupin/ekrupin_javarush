package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Евгений on 06.10.2016.
 */
public class SpeedTest {

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet();
        for (int i = 0; i <10000 ; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids1 = new HashSet();
        Set<Long> ids2 = new HashSet();
        Long time1 = getTimeForGettingIds(shortener1, origStrings, ids1);
        Long time2 = getTimeForGettingIds(shortener2, origStrings, ids2);
        Assert.assertTrue(time1>time2);

        Set<String> strings1 = new HashSet();
        Set<String> strings2 = new HashSet();
        time1 = getTimeForGettingStrings(shortener1, ids1, strings1);
        time2 = getTimeForGettingStrings(shortener2, ids2, strings2);
        Assert.assertEquals(time1, time2, 5);
    }

    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date dateBegin = new Date();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }
        Date dateEnd = new Date();
        return dateEnd.getTime()-dateBegin.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date dateBegin = new Date();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        Date dateEnd = new Date();
        return dateEnd.getTime()-dateBegin.getTime();
    }

}
