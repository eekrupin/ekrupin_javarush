package com.javarush.test.level19.lesson05.task05;

/* Пунктуация
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Удалить все знаки пунктуации, включая символы новой строки. Результат вывести во второй файл.
http://ru.wikipedia.org/wiki/%D0%9F%D1%83%D0%BD%D0%BA%D1%82%D1%83%D0%B0%D1%86%D0%B8%D1%8F
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
            fw.write(br.readLine().replaceAll("\\p{Punct}", ""));
        }

        fw.close();
        reader.close();
        br.close();
    }

}
