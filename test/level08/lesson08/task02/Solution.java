package com.javarush.test.level08.lesson08.task02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* Удалить все числа больше 10
Создать множество чисел(Set<Integer>), занести туда 20 различных чисел.
Удалить из множества все числа больше 10.
*/

public class Solution
{
    public static void main(String[] args) {
        HashSet<Integer> set = createSet();
        removeAllNumbersMoreThan10(set);
        for (Integer i:set) {
            System.out.println(i);
        }
    }

    public static HashSet<Integer> createSet()
    {
        HashSet<Integer> set = new HashSet<>();
        set.add(5);
        set.add(3);
        set.add(7);
        set.add(15);
        set.add(2);
        set.add(25);
        set.add(35);
        set.add(55);
        set.add(65);
        set.add(75);

        set.add(115);
        set.add(113);
        set.add(117);
        set.add(1115);
        set.add(112);
        set.add(1125);
        set.add(1135);
        set.add(1155);
        set.add(1165);
        set.add(1175);

        return set;

    }

    public static HashSet<Integer> removeAllNumbersMoreThan10(HashSet<Integer> set)
    {
        ArrayList<Integer> arr = new ArrayList<>();
        for (Integer i:set) {
            if(i>10) arr.add(i);
        }

        for (Integer i:arr) {
            set.remove(i);
        }

        return set;
    }
}
