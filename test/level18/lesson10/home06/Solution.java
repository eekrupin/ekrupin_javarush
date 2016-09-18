package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки. Не использовать try-with-resources

Пример вывода:
, 19
- 7
f 361
*/

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length==0) return;
        String name = args[0];
        File file = new File(name);
        char[] chars = new char[(int)file.length()];
        FileReader reader = new FileReader(name);
        reader.read(chars);

        TreeMap<Integer, CharCount> tree = new TreeMap<>();

        for (char ch:chars) {
            if (!tree.containsKey((int)ch)){
                tree.put((int)ch, new CharCount(ch, 0));
            }
            CharCount charCount = tree.get((int)ch);
            charCount.setCount(charCount.getCount()+1);
        }
        for (Map.Entry<Integer, CharCount> pair:tree.entrySet()) {
            System.out.println(pair.getValue().simbol + " " + pair.getValue().count);
        }
        reader.close();
    }

    private static class CharCount{
        private char simbol;
        private int count;

        public CharCount(char simbol, int count) {
            this.simbol = simbol;
            this.count = count;
        }

        public char getSimbol() {
            return simbol;
        }

        public void setSimbol(char simbol) {
            this.simbol = simbol;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}

