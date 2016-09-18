package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import com.sun.org.apache.xalan.internal.xsltc.compiler.*;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.*;
import java.util.regex.*;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fr = new FileReader(reader.readLine());
        BufferedReader br = new BufferedReader(fr);

        Pattern p = Pattern.compile("world", Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
        int count = 0;
        while (br.ready()){
            String s = br.readLine();
            Matcher m = p.matcher(s);
            while (m.find()) count++;
        }
        System.out.println(count);
        br.close();
        fr.close();
        reader.close();

    }
}
