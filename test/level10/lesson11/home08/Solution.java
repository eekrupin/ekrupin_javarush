package com.javarush.test.level10.lesson11.home08;

import java.util.ArrayList;
import java.util.Date;

/* Массив списков строк
Создать массив, элементами которого будут списки строк. Заполнить массив любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<String>[] arrayOfStringList =  createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList()
    {
        ArrayList<String>[] arr = new ArrayList[5];
        for (int i=0; i<arr.length; i++) {
            ArrayList<String> al = new ArrayList<>();
            al.add("dsds1" + new Date());
            al.add("dsds2" + new Date());
            al.add("dsds3" + new Date());
            al.add("-");

            arr[i] = al;

        }

        return arr;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList)
    {
        for (ArrayList<String> list: arrayOfStringList)
        {
            for (String s : list)
            {
                System.out.println(s);
            }
        }
    }
}