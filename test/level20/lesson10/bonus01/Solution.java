package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static void main(String[] args) {
        long memoryStart = Runtime.getRuntime().freeMemory();
        long startTime = System.currentTimeMillis();

        System.out.println(Arrays.toString(getNumbers(500000)));

        long estimatedTime = System.currentTimeMillis() - startTime;
        long memoryEnd = Runtime.getRuntime().freeMemory();
        long estimatedMemory = memoryStart - memoryEnd;
        System.out.println("Время подсчета: " + (double) estimatedTime / 1000 + " c");
        System.out.println("Использованная память: " + (double) estimatedMemory / (1024 * 1024) + " МБ");
    }

    public static int[] getNumbers(int N) {

        int[][] b = new int[11][12];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                int a = 1;
                for (int k = 0; k < j; k++) {
                    a = a * i;
                }
                b[i][j] = a;
            }
        }

        TreeSet<Integer> set = new TreeSet();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            int[] a = new int[(int) (Math.log10(i) + 1)];
            int q = 0;
            int t = i;
            while (t > 0) {
                int f = t % 10;
                a[q] = f;
                t = t / 10;
                q++;
            }


            for (int j = 0; j < a.length; j++) {
                try {
                    while (a[j] < a[j + 1]) {
                        a[j] = a[j] + 1;
                        j = 0;
                    }
                } catch (Exception e) {
                }
            }



            int h = 0;
            for (int k = 0; k < a.length; k++) {
                h = h + (a[a.length - k - 1] * b[10][a.length - k - 1]);
            }
            i = h;



            for (int j = 0; j < 4; j++) {
                int m = (int) Math.log10(h) + 1;
                int sym = 0;
                int w = h;
                while (w > 0) {
                    int c = w % 10;
                    sym += b[c][m];
                    w = w / 10;
                }
                int starms = (int) Math.log10(sym) + 1;
                int arms = 0;
                int e = sym;
                while (e > 0) {
                    int c = e % 10;
                    arms += b[c][starms];
                    e = e / 10;
                }
                if (arms == sym && arms != 0) {
                    set.add(arms);
                }
                h = h * 10;
            }
            if (i < 0) break;
        }

        int y = 0;
        for (int c: set){
            if (c > N) break;
            list.add(c);
            y++;
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
