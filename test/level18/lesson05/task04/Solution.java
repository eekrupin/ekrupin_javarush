package com.javarush.test.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        FileOutputStream outputStream = new FileOutputStream(reader.readLine());

        byte[] source = new byte[inputStream.available()];
        if (inputStream.available()>0){
            inputStream.read(source);

            for (int i = source.length-1; i>=0; i--) {
                outputStream.write(source[i]);
            }
        }

        outputStream.close();
        inputStream.close();
        reader.close();

    }
}
