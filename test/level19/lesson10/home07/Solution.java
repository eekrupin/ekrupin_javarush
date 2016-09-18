package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fn1 = args[0];
        String fn2 = args[1];

        FileReader fr1 = new FileReader(fn1);
        BufferedReader br = new BufferedReader(fr1);

        FileWriter fw2 = new FileWriter(fn2);

        while (br.ready()){
            String[] lines = br.readLine().split(" ");

            Boolean needSpace = false;
            Boolean needNewLine = false;
            for (String world : lines) {
                if (world.length()>6) {
                    if (needSpace) fw2.write(",");

                    fw2.write(world);
                    needSpace = true;
                    needNewLine = true;

                }
            }
            if (needNewLine) fw2.write(System.getProperty("line.separator"));
        }

        br.close();
        fr1.close();
        fw2.close();
    }
}
