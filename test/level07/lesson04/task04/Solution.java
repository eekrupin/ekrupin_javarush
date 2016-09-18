package com.javarush.test.level07.lesson04.task04;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Массив из чисел в обратном порядке
1. Создать массив на 10 чисел.
2. Ввести с клавиатуры 10 чисел и записать их в массив.
3. Расположить элементы массива в обратном порядке.
4. Вывести результат на экран, каждое значение выводить с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] i = new int[10];
        for (int j = 1; j <=10 ; j++){
            i[j-1] = Integer.parseInt(br.readLine());
        }

        ReversArr(i);

        for (int j = 1; j <=10 ; j++){
            System.out.println(i[j-1]);
        }

    }

    public static void ReversArr(int[] i){
        int temp;
        int totalInd = i.length - 1;
        for (int j = 0; j <= totalInd ; j++)
        {
            temp =i[j];
            i[j]=i[totalInd-j];
            i[totalInd-j] = temp;
            if (j+1==i.length/2) break;
        }
}

}
