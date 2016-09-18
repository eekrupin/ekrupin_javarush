package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;



public class Solution {
    public static String[] ss;
    public static void main(String[] args) throws IOException {

        Map<Integer ,byte[]> map = new TreeMap<Integer, byte[]>();
        int part;
        String FinFile;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        while (true)
        {
            try
            {
                fileName = br.readLine();
                if (!fileName.equals("end")){
                    ss = fileName.split(".part");
                    part = Integer.parseInt(ss[1]);
                    FileInputStream fis = new FileInputStream(fileName);
                    byte[] b1 = new byte[fis.available()];
                    fis.read(b1);
                    map.put(part, b1);
                    fis.close();
                }
                else break;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        FinFile = ss[0];
        FileOutputStream fos = new FileOutputStream(FinFile);
        for (Map.Entry<Integer, byte[]> pair : map.entrySet())
        {
            System.out.println(pair.getKey());
            try
            {
                fos.write(pair.getValue());
            }
            catch (FileNotFoundException e)
            {
                System.out.print("не нашел");
            }
            catch (IOException e)
            {
                System.out.print("ошибка ввода");
            }
        }
        fos.close();
        br.close();

       /* BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> listOfNames = new ArrayList<>();

        String fileName;
        while (true){
            try {
                fileName = reader.readLine();
            }
            catch (Exception e){continue;}
            if (fileName.equals("end")) break;
            if (!listOfNames.contains(fileName)) listOfNames.add(fileName);
        }

        Collections.sort(listOfNames);

        if (listOfNames.size()>0){
            fileName = getFileName(listOfNames.get(0));
            try {
                FileOutputStream out = new FileOutputStream(fileName);
                out.close();
                out = new FileOutputStream(fileName, true);

                for (String partOfFile:listOfNames) {
                    InputStream input = new FileInputStream(partOfFile);
                    byte[] b = new byte[input.available()];
                    if (b.length > 0) {
                        input.read(b);
                        out.write(b);
                    }
                    input.close();
                }
                out.flush();
                out.close();
            }
            catch (FileNotFoundException e) {
                return;
            } catch (IOException e) {
                return;
            }
        }

        try {
            reader.close();
        }
        catch (Exception e)
        {return;}*/

    }

    private static String getFileName(String s) {
        return new String(s.substring(0, s.lastIndexOf('.')));
    }
}
