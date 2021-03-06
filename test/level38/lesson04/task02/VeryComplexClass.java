package com.javarush.test.level38.lesson04.task02;

/* Непроверяемые исключения (unchecked exception)
Напиши реализацию метода methodThrowsClassCastException(). Он должен
всегда кидать непроверяемое исключение ClassCastException.

Напиши реализацию метода methodThrowsNullPointerException(). Он должен
всегда кидать непроверяемое исключение NullPointerException.

Кинуть исключение (throw) явно нельзя.
*/

import java.util.List;

public class VeryComplexClass {


    public void methodThrowsClassCastException() {
        Object obj = new String();
        int c = (Integer)obj;

    }

    public void methodThrowsNullPointerException() {
        List<Integer> list = null;
        list.add(null);

    }
}
