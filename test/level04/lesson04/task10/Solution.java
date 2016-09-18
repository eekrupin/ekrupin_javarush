package com.javarush.test.level04.lesson04.task10;

/* Три числа
Ввести с клавиатуры три целых числа. Определить, имеется ли среди них хотя бы одна пара равных между собой чисел.
Если такая пара существует, вывести на экран числа через пробел. Если все три числа равны между собой, то вывести все три.
Пример для чисел 1 2 2:
2 2
Пример для чисел 2 2 2:
2 2 2
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String с1 = br.readLine();
        String с2 = br.readLine();
        String с3 = br.readLine();

        int s1 = Integer.parseInt(с1);
        int s2 = Integer.parseInt(с2);
        int s3 = Integer.parseInt(с3);

        String text;
        if(s1==s2 & s1==s3 & s2==s3) text = s1 + " " + s2 + " " + s3;
        else if(s1==s2) text = s1 + " " + s2;
        else if(s1==s3) text = s1 + " " + s3;
        else if(s2==s3) text = s2 + " " + s3;
        else text = "";

        System.out.println(text);

    }
}