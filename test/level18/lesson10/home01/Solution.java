package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream stream = new FileInputStream(args[0]);

        int count = 0;
        while (stream.available()>0) {
            int i = stream.read();
            if (i >= 65 & i <= 90 | i >= 97 & i <= 122) count++;
        }
        stream.close();
        System.out.println(count);
    }
}
