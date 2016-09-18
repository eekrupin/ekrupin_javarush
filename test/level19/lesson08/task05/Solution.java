package com.javarush.test.level19.lesson08.task05;

/* Дублируем текст
Считайте с консоли имя файла
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна дублировать вывод всего текста в файл, имя которого вы считали
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток
Закройте поток файла

Пример вывода на экран:
it's a text for testing

Пример тела файла:
it's a text for testing
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fw = new FileWriter(reader.readLine());

        PrintStream consoleStream = System.out;
        ByteArrayOutputStream arrOut = new ByteArrayOutputStream();
        PrintStream outStream = new adapterWriterStream(arrOut);
        System.setOut(outStream);
        testString.printSomething();
        System.setOut(consoleStream);
        fw.write(arrOut.toString());
        System.out.println(arrOut.toString());
        fw.close();
        reader.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }

    private static class adapterWriterStream extends PrintStream {
        public adapterWriterStream(ByteArrayOutputStream arrOut) {
            super(new PrintStream(arrOut));
        }
    }
}

