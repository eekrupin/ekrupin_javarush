package com.javarush.test.level18.lesson10.home07;

/* Поиск данных внутри файла
Считать с консоли имя файла
Найти в файле информацию, которая относится к заданному id, и вывести ее на экран в виде, в котором она записана в файле.
Программа запускается с одним параметром: id (int)
Закрыть потоки. Не использовать try-with-resources

В файле данные разделены пробелом и хранятся в следующей последовательности:
id productName price quantity

где id - int
productName - название товара, может содержать пробелы, String
price - цена, double
quantity - количество, int

Информация по каждому товару хранится в отдельной строке
*/

import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader input = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();
        String b;
        while ((b=input.readLine())!=null){
            if (b.startsWith(args[0] + " ")){
                System.out.println(b);
                break;
            }
        }
        input.close();
    }

    /*public static void main(String[] args) throws IOException {
        String fileName = getFileNameFromConsole();
        BufferedReader reader = getReaderFile(fileName);
        String line = getLineById(reader, args[0]);
        System.out.println(line);
        reader.close();
    }

    private static String getLineById(BufferedReader reader, String id) throws IOException {
        String line = "";
        while (reader.ready()){
            line = reader.readLine();
            if (line.startsWith(id + " ")) break;
        }
        return line;
    }

    private static BufferedReader getReaderFile(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "windows-1251"));
        return reader;
    }

    static String getFileNameFromConsole() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        return fileName;
    }*/
}
