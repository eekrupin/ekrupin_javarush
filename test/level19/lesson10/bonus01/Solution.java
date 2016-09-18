package com.javarush.test.level19.lesson10.bonus01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
               строка0            ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
               строка5            ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String fn1 = consoleReader.readLine();
        String fn2 = consoleReader.readLine();

//        BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(fn1), "windows-1251"));
//        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(fn2), "windows-1251"));

        BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(fn1)));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(fn2)));

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        while (br1.ready()){
            list1.add(br1.readLine());
        }
        while (br2.ready()){
            list2.add(br2.readLine());
        }

        int maxInd = list1.size()>=list2.size()?list1.size()-1:list2.size()-1;
        int shift1 = 0;
        int shift2 = 0;
        for (int ind = 0 ; ind<=maxInd; ind++) {
            String s1 = getValueFromList(list1, ind, shift1);
            String s2 = getValueFromList(list2, ind, shift2);

            if (s1.equals(s2)) lines.add(new LineItem(Type.SAME, s1));
            else if (s1.equals(getValueFromList(list2, ind, shift2+1))) {
                lines.add(new LineItem(Type.ADDED, getValueFromList(list2, ind, shift2)));
                shift2++;
                lines.add(new LineItem(Type.SAME, s1));
            }
            else if (s2.equals(getValueFromList(list1, ind, shift1+1))) {
                lines.add(new LineItem(Type.REMOVED, getValueFromList(list1, ind, shift1)));
                shift1++;
                lines.add(new LineItem(Type.SAME, s2));
            }
        }


        br1.close();
        br2.close();
        consoleReader.close();

        for (LineItem line : lines) {
            System.out.println(line.type + " " + line.line);
        }


    }

    private static String getValueFromList(ArrayList<String> list, int ind, int shift){
        return list.size()-1>=ind+shift ? list.get(ind+shift) : "";
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}