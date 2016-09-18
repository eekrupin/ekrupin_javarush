package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        String namefile1 = read.readLine();
        FileOutputStream file1 = new FileOutputStream(namefile1);
        FileInputStream file2 = new FileInputStream(read.readLine());
        String namefile2 = read.readLine();
        FileReader fileReader = new FileReader(namefile2);
        read.close();

        byte[] b = new byte[file2.available()];
        if (file2.available() > 0) {
            file2.read(b);
            file1.write(b);
        }

        file1.close();
        file2.close();


        char[] ch = new char[(int)new File(namefile2).length()];
        fileReader.read(ch);
        FileWriter fw = new FileWriter(namefile1, true);
        if (ch.length > 0) {
            fw.write(ch);
        }
        fileReader.close();
        fw.close();
    }
}
