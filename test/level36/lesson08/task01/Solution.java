package com.javarush.test.level36.lesson08.task01;

import javax.xml.stream.events.Characters;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //String path = "E:\\Java\\1.txt";
        String path = args[0];
        TreeSet<Character> characters = new TreeSet<>();
        try (FileReader fileReader = new FileReader(path)) {
            BufferedReader reader = new BufferedReader(fileReader);
            Character ch;
            while (reader.ready()) {
                ch = (char) fileReader.read();
                if (Character.isAlphabetic(ch)) characters.add(Character.toLowerCase(ch));
            }
        }catch (Exception e){}

       int i = 5;
        for (Character character : characters) {
            System.out.print(character);
            i--;
            if (i==0) break;
        }

    }
}
