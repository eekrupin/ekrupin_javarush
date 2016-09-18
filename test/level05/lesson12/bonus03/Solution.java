package com.javarush.test.level05.lesson12.bonus03;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/* Задача по алгоритмам
Написать программу, которая:
1. вводит с консоли число N > 0
2. потом вводит N чисел с консоли
3. выводит на экран максимальное из введенных N чисел.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        Integer arr[] = new Integer[count];
        for (int i = 1; i <= count ; i++){
            arr[i-1] = Integer.parseInt(reader.readLine());
        }

        Arrays.sort(arr, Collections.reverseOrder());
        int maximum = arr[0];

        System.out.println(maximum);
    }
}
