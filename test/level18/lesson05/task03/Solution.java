package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        FileInputStream file1 = new FileInputStream(read.readLine());
        FileOutputStream file2 = new FileOutputStream(read.readLine());
        FileOutputStream file3 = new FileOutputStream(read.readLine());
        read.close();
        int c = (file1.available() / 2) + 1;
        while (file1.available() > 0) {
            if (file1.available() < c) file3.write(file1.read());
            else file2.write(file1.read());
        }
        file1.close();
        file2.close();
        file3.close();
    }
}