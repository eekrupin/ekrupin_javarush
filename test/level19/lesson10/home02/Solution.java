package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {

        Map<String, Double> map = new HashMap<>();

        FileReader fr = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fr);

        Double max = 0d;
        while (br.ready()){
            String[] lines = br.readLine().split(" ");
            if (!map.containsKey(lines[0])) map.put(lines[0], Double.parseDouble(lines[1]));
            else map.put(lines[0], map.get(lines[0]) + Double.parseDouble(lines[1]));
            max = max<=map.get(lines[0]) ? map.get(lines[0]) : max;
        }

        for (Map.Entry<String, Double> pair:map.entrySet()) {
            if (pair.getValue()==max) System.out.println(pair.getKey());
        }

        br.close();
        fr.close();

       /* Map<String, Double> map = new HashMap<>();

        FileInputStream fis = new FileInputStream(args[0]);
        InputStreamReader isr = new InputStreamReader(fis, "windows-1251");
        BufferedReader br = new BufferedReader(isr);

        Double max = 0d;
        while (br.ready()){
            String[] lines = br.readLine().split(" ");
            if (!map.containsKey(lines[0])) map.put(lines[0], Double.parseDouble(lines[1]));
            else map.put(lines[0], map.get(lines[0]) + Double.parseDouble(lines[1]));
            max = max<=map.get(lines[0]) ? map.get(lines[0]) : max;
        }

        for (Map.Entry<String, Double> pair:map.entrySet()) {
            if (pair.getValue()==max) System.out.println(pair.getKey());
        }

        br.close();
        isr.close();
        fis.close();*/


    }
}
