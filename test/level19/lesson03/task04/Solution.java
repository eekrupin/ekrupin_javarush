package com.javarush.test.level19.lesson03.task04;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution {
    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner scanner;

        public static void main(String[] args) throws IOException {
            Scanner scanner = new Scanner("Иванов Иван Иванович 31 12 1950");
            PersonScannerAdapter personScannerAdapter = new PersonScannerAdapter(scanner);
            Person person = personScannerAdapter.read();
            System.out.println(person.toString());
        }

        public PersonScannerAdapter(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public Person read() throws IOException {
            String line = scanner.nextLine();
            String[] lineArr = line.split(" ");
            Person person = new Person(lineArr[1], lineArr[2], lineArr[0], new Date(Integer.parseInt(lineArr[5])-1900, Integer.parseInt(lineArr[4])-1, Integer.parseInt(lineArr[3])));
            return person;
        }

        @Override
        public void close() throws IOException {
            scanner.close();
        }
    }
}
