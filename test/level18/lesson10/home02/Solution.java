package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream stream = new FileInputStream(args[0]);

        int countAll = 0;
        int countSpaceBar = 0;
        int i;
        while (stream.available()>0) {
            countAll++;
            i = stream.read();
            if (i==32) countSpaceBar++;
        }

        double d = (double)countSpaceBar/countAll*100;
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        String format = decimalFormat.format(d);
        System.out.println(format);
        stream.close();
    }
}
