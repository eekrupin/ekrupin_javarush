package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * https://mvnrepository.com/artifact/junit/junit/4.11
 * https://mvnrepository.com/artifact/org.hamcrest/hamcrest-core/1.3
 */
public class FunctionalTest{
    public void testStorage(Shortener shortener){
        String s1 = Helper.generateRandomString();
        String s2 = Helper.generateRandomString();
        String s3 = new String(s1);

        Long l1 = shortener.getId(s1);
        Long l2 = shortener.getId(s2);
        Long l3 = shortener.getId(s3);

        Assert.assertNotEquals(l2, l1);
        Assert.assertNotEquals(l2, l3);
        Assert.assertEquals(l1, l3);

        String s1FromShortener = shortener.getString(l1);
        String s2FromShortener = shortener.getString(l2);
        String s3FromShortener = shortener.getString(l3);

        Assert.assertEquals(s1, s1FromShortener);
        Assert.assertEquals(s2, s2FromShortener);
        Assert.assertEquals(s3, s3FromShortener);

    }

    @Test
    public void testHashMapStorageStrategy(){
        testStorage(new Shortener(new HashMapStorageStrategy()));
    }

    @Test
    public void testOurHashMapStorageStrategy(){
        testStorage(new Shortener(new OurHashMapStorageStrategy()));
    }

    @Test
    public void testFileStorageStrategy(){
        testStorage(new Shortener(new FileStorageStrategy()));
    }

    @Test
    public void testHashBiMapStorageStrategy(){
        testStorage(new Shortener(new HashBiMapStorageStrategy()));
    }

    @Test
    public void testDualHashBidiMapStorageStrategy(){
        testStorage(new Shortener(new DualHashBidiMapStorageStrategy()));
    }

    @Test
    public void testOurHashBiMapStorageStrategy(){
        testStorage(new Shortener(new OurHashBiMapStorageStrategy()));
    }
}
