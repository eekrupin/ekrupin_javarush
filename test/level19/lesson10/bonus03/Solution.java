package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        String teg = args[0];
        String openTeg1 = "<" + args[0] + " ";
        String openTeg2 = "<" + args[0] + ">";
        String closeTeg = "</" + args[0] + ">";
        int openTegLenght = openTeg1.length();
        int closeTegLenght = closeTeg.length();

        try {
        String fn = getFileName();
        //FileInputStream fileInput =  new FileInputStream(fn);
        //InputStreamReader inputStreamReader = new InputStreamReader(fileInput);
        //BufferedReader readerFile = new BufferedReader(inputStreamReader);
        FileReader fr = new FileReader(fn);
        BufferedReader readerFile = new BufferedReader(fr);

            FileReader fileRead = null;
            try
            {
                fileRead = new FileReader(fn);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            String fileString = "";
            try
            {
                while (fileRead.ready())
                {
                    fileString += (char) fileRead.read();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }



        ArrayList<StringBuilder> arrSb = new ArrayList<>();
        ArrayList<Integer> stack = new ArrayList<>();
        //while (readerFile.ready()){
            //String line = readerFile.readLine().replaceAll("[\n\r]", "");
            String line = fileString.replaceAll("[\n\r]", "");
            int lineLength = line.length();
            int commitInd = -1;
            for (int ind = 0 ; ind < lineLength; ind++) {

                String subText = "";
                if (ind<=lineLength-openTegLenght){
                    subText = line.substring(ind, ind+openTegLenght);
                }

                if (subText.equals(openTeg1) || subText.equals(openTeg2)) {
                    StringBuilder sb = new StringBuilder(new String(subText));
                    arrSb.add(sb);

                    for (Integer iStack:stack) {
                        arrSb.get(iStack).append(new String(subText));
                    }

                    stack.add(arrSb.size()-1);
                    commitInd = ind + openTegLenght-1;
                }
                else if (ind+closeTegLenght<=lineLength && line.substring(ind, ind+closeTegLenght).equals(closeTeg)){
                    String subTextClose = new String(line.substring(ind, ind+closeTegLenght));
                    for (Integer iStack:stack) {
                        arrSb.get(iStack).append(subTextClose);
                    }
                    stack.remove(stack.size()-1);
                    commitInd = ind + closeTegLenght-1;
                }
                else if (ind>commitInd && stack.size()>0){
                    char ch = line.charAt(ind);
                    for (Integer iStack:stack) {
                        arrSb.get(iStack).append(ch);
                    }
                    commitInd = ind;
                }

            }
        //}

        for (StringBuilder stringBuilder : arrSb) {
            System.out.println(stringBuilder.toString());
        }

        readerFile.close();
        fr.close();
        //inputStreamReader.close();
        //fileInput.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private static String getFileName() throws IOException {
        String fn;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fn = reader.readLine();
        reader.close();
        return fn;
    }
}