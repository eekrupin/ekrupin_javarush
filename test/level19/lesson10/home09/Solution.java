package com.javarush.test.level19.lesson10.home09;

/* Контекстная реклама
В методе main подмените объект System.out написанной вами реадер-оберткой
Ваша реадер-обертка должна выводить на консоль контекстную рекламу после каждого второго println-а
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Рекламный текст: "JavaRush - курсы Java онлайн"

Пример вывода:
first
second
JavaRush - курсы Java онлайн
third
fourth
JavaRush - курсы Java онлайн
fifth
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream arrByte = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(arrByte);

        System.setOut(out);
        testString.printSomething();
        System.setOut(consoleStream);

        String[] lines = arrByte.toString().split(System.getProperty("line.separator"));

        int count = 0;
        for (String line : lines) {
            if (count%2==0&&count>0) System.out.println("JavaRush - курсы Java онлайн");
            System.out.println(line);
            count++;
        }


    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
