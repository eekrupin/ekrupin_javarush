package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        String namefile1 = read.readLine();
        FileInputStream file1 = new FileInputStream(namefile1);
        String namefile2 = read.readLine();
        FileInputStream file2 = new FileInputStream(namefile2);
        read.close();

        byte[] b1 = new byte[file1.available()];
        file1.read(b1);
        byte[] b2 = new byte[file2.available()];
        file2.read(b2);

        file1.close();
        file2.close();

        FileOutputStream out = new FileOutputStream(namefile1);
        out.write(b2);
        out.write(b1);
        out.close();

    }
}
