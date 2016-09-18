package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байт или байты с минимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        HashMap<Integer, Integer> map = new HashMap<>();
        Integer i;
        Integer min = 500;
        FileInputStream inputStream = new FileInputStream(fileName);
        while (inputStream.available()>0){
            i = inputStream.read();
            if (!map.containsKey(i)) map.put(i, 0);
            map.put(i, map.get(i)+1);
            min = (map.get(i) < min) ? map.get(i): min;
        }

        inputStream.close();
        reader.close();

        for (Map.Entry<Integer, Integer> pair:map.entrySet()) {
            if (pair.getValue()==min) System.out.print(pair.getKey() + " ");
        }

    }
}
