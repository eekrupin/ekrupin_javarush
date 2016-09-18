package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();

        FileReader fileReader = new FileReader(fileName1);
        FileOutputStream out = new FileOutputStream(fileName2);

        File file1 = new File(fileName1);
        char[] chars = new char[(int)file1.length()];

        StringBuilder sb = new StringBuilder();
        boolean beginNew = false;
        ArrayList<Integer> ints = new ArrayList<>();
        if (fileReader.read(chars)!=-1){
            for (char ch:chars) {
                if (ch==' '){
                    beginNew = true;
                    continue;
                }
                if (beginNew){
                    ints.add((int)Math.round(Double.parseDouble(sb.toString())));
                    sb = new StringBuilder();
                    beginNew = false;
                }
                sb.append(ch);
            }
        }
        if (sb.length()>0) ints.add((int)Math.round(Double.parseDouble(sb.toString())));
        fileReader.close();

        boolean needSpace = false;
        for (int i:ints) {
            if (needSpace) out.write(" ".getBytes());
            out.write(Integer.toString(i).getBytes());
            needSpace = true;
        }
        out.close();
    }

}
