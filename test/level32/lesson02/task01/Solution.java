package com.javarush.test.level32.lesson02.task01;

import java.io.RandomAccessFile;

/* Запись в файл
В метод main приходят три параметра:
1) fileName - путь к файлу
2) number - число, позиция в файле
3) text - текст
Записать text в файл fileName начиная с позиции number.
Если файл слишком короткий, то записать в конец файла.
*/
public class Solution {
    public static void main(String... args) {
//        String fileName = args[0];
//        int number = Integer.parseInt(args[0]);
//        String text = args[0];

        String fileName = "D:\\Java\\TestData\\Random.txt";
        int number = 14;
        String text = "!!!55555--666666 55555--666666";

        try (RandomAccessFile file = new RandomAccessFile(fileName, "rw")){
            int lenght = text.length();
            if (file.length()-number>=lenght) file.seek(number);
            else file.seek(file.length());
            byte[] bytes = new byte[lenght];
            file.write(text.getBytes());
        } catch (java.io.IOException e) {
        }

    }
}
