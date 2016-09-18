package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream arrayStream = new ByteArrayOutputStream();
        PrintStream outStream = new PrintStream(arrayStream);
        System.setOut(outStream);
        testString.printSomething();
        String inString = arrayStream.toString();
        System.setOut(consoleStream);

        StringBuffer javascript = null;
        ScriptEngine runtime = null;

        try {
            runtime = new ScriptEngineManager().getEngineByName("javascript");
            javascript = new StringBuffer();
            javascript.append(inString.replace("=", ""));

            int result = (int)(double)runtime.eval(javascript.toString());

            System.out.println(inString.trim() + " " + result);
        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

