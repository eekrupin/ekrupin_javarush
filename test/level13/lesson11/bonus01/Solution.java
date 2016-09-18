package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution
{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();

        FileInputStream inputStream = new FileInputStream(name);
        ArrayList<Integer> list =new ArrayList<>();
        String text = "";
        while (inputStream.available()>0){
            text = text.concat(Character.toString((char)inputStream.read()));
        }
        String[] arrayString = text.split(System.lineSeparator());
        for (String s: arrayString) {
            Integer i = Integer.parseInt(s);
            if(i%2*1.0==0) list.add(i);
        }

        Collections.sort(list);

        for (int i:list) {
            System.out.println(i);
        }

    }
}
