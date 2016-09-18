package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Самые частые байты
Ввести с консоли имя файла
Найти байт или байты с максимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        HashMap<Integer, Integer> map = new HashMap<>();
        Integer i;
        Integer max = 0;
        FileInputStream inputStream = new FileInputStream(fileName);
        while (inputStream.available()>0){
            i = inputStream.read();
            if (!map.containsKey(i)) map.put(i, 0);
            map.put(i, map.get(i)+1);
            max = (map.get(i) > max) ? map.get(i): max;
        }

        for (Map.Entry<Integer, Integer> pair:map.entrySet()) {
            if (pair.getValue()==max) System.out.print(pair.getKey() + " ");
        }

                    }
}
