package com.javarush.test.level07.lesson06.task03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Самая короткая строка
1. Создай список строк.
2. Считай с клавиатуры 5 строк и добавь в список.
3. Используя цикл, найди самую короткую строку в списке.
4. Выведи найденную строку на экран.
5. Если таких строк несколько, выведи каждую с новой строки.
*/
public class Solution
{
    public static void main(String[] args) throws Exception
    {
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <=5 ; i++)
        {
            list.add(br.readLine());
        }

        int minLenth = list.get(0).length();
        for (int i = 0; i < list.size() ; i++){
            minLenth = minLenth > list.get(i).length() ? list.get(i).length():minLenth;
        }

        for (int i = 0; i < list.size() ; i++){
            if(list.get(i).length() == minLenth) System.out.println(list.get(i));
        }

    }
}
