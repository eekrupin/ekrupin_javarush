package com.javarush.test.level19.lesson10.home03;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fr);

//        FileInputStream fis = new FileInputStream(args[0]);
//        InputStreamReader isr = new InputStreamReader(fis, "windows-1251");
//        BufferedReader br = new BufferedReader(isr);

        Double max = 0d;
        while (br.ready()){
            String[] lines = br.readLine().split(" ");

            int lastInd = lines.length-1;
            StringBuilder sb = new StringBuilder();
            Boolean needSpace = false;
            for (int i = 0; i < lastInd-2; i++) {
                if (needSpace)sb.append(' ');
                else needSpace = true;
                sb.append(lines[i]);

            }
            Date date = new Date(Integer.parseInt(lines[lastInd])-1900, Integer.parseInt(lines[lastInd-1])-1, Integer.parseInt(lines[lastInd-2]));

            Person person = new Person(sb.toString(),  date);
            PEOPLE.add(person);
        }

        br.close();
        fr.close();

//        isr.close();
//        fis.close();

    }

}
