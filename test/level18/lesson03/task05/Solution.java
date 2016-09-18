package com.javarush.test.level18.lesson03.task05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;




/* Сортировка байт
Ввести с консоли имя файла
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран
Закрыть поток ввода-вывода

Пример байт входного файла
44 83 44

Пример вывода
44 83
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Integer i;
         FileInputStream inputStream = new FileInputStream(fileName);
        while (inputStream.available()>0){
            i = inputStream.read();
            if (!map.containsKey(i)) map.put(i, 0);
            map.put(i, map.get(i)+1);
        }

        inputStream.close();
        reader.close();

        for (Map.Entry<Integer, Integer> pair:map.entrySet()) {
            System.out.print(pair.getKey() + " ");
        }
    }
}
