package com.javarush.test.level14.lesson08.bonus03;

/**
 * Created by Евгений on 09.08.2016.
 */
public class Singleton {
    private static Singleton singleton = new Singleton();

    private Singleton() {

    }

    public static Singleton getInstance(){
       return singleton;
    }
}
