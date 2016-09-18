package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String f1 = reader.readLine();
        String f2 = reader.readLine();
        ArrayList<Integer> numerics = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(f1));
        FileWriter fw = new FileWriter(f2);

        while (br.ready()){
            fw.write(br.readLine().replace(".", "!"));
        }

        fw.close();
        reader.close();
        br.close();
    }
}
