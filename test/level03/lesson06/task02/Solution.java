package com.javarush.test.level03.lesson06.task02;

/* Таблица умножения
Выведи на экран таблицу умножения 10 на 10 в следующем виде:
1 2 3 …
2 4 6 …
3 6 9 …
…
*/

public class Solution
{
    public static void main(String[] args)
    {
        for (int y = 1; y<11;y++){

            for (int x = 1; x<11;x++){
                int value = x*y;
                String toScreen = Integer.toString(value) + " ";
                System.out.print(toScreen);
            }
            System.out.println();

        }

    }
}