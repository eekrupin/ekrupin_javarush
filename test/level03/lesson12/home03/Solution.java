package com.javarush.test.level03.lesson12.home03;

/* Я буду зарабатывать $50 в час
Ввести с клавиатуры число n.
Вывести на экран надпись «Я буду зарабатывать $n в час».
Пример:
Я буду зарабатывать $50 в час
*/
import java.io.*;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        InputStream inputStream = System.in;
        Reader inputStremReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStremReader);

        String dollar = bufferedReader.readLine();
        System.out.println("Я буду зарабатывать $" + dollar + " в час");
    }
}