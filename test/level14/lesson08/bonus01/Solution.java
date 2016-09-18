package com.javarush.test.level14.lesson08.bonus01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        try{float i = 1 / 0;} catch (Exception e){exceptions.add(e);}
        try{throw new RuntimeException();} catch (Exception e){exceptions.add(e);}
        try{throw new ArithmeticException();} catch (Exception e){exceptions.add(e);}
        try{throw new ArrayIndexOutOfBoundsException ();} catch (Exception e){exceptions.add(e);}
        try{throw new ArrayStoreException();} catch (Exception e){exceptions.add(e);}
        try{throw new IllegalThreadStateException ();} catch (Exception e){exceptions.add(e);}
        try{throw new IllegalArgumentException();} catch (Exception e){exceptions.add(e);}
        try{throw new IndexOutOfBoundsException ();} catch (Exception e){exceptions.add(e);}
        try{throw new NumberFormatException  ();} catch (Exception e){exceptions.add(e);}
        try{throw new SecurityException  ();} catch (Exception e){exceptions.add(e);}

        //Add your code here

    }
}
