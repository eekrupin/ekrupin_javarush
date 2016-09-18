package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со слов, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
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
                if (world.matches(".*\\p{Digit}.*")) {
                    if (needSpace) fw2.write(" ");

                    fw2.write(world + " ");
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
