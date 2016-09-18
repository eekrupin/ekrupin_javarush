package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
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
        String[] arrLine;
        while (br.ready()){
            arrLine = br.readLine().split(" ");
            for (String s:arrLine) {
                try {
                    int i = Integer.parseInt(s);
                    numerics.add(i);
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }

        }
        String res = "";
        for (int i:numerics) {
            res+= i + " ";
        }

        FileWriter fw = new FileWriter(f2);
        fw.write(res.trim());
        fw.close();

        reader.close();
        br.close();

    }
}
