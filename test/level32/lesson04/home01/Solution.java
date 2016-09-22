package com.javarush.test.level32.lesson04.home01;

import java.io.*;

/* Читаем из потока
Реализуйте логику метода getAllDataFromInputStream. Он должен вернуть StringWriter, содержащий все данные из переданного потока.
Возвращаемый объект ни при каких условиях не должен быть null.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("src\\com\\javarush\\test\\level32\\lesson04\\home01\\testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        if (is!=null) {
            char[] chars = new char[5];
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            int count;
            while (inputStreamReader.ready()){
                count = inputStreamReader.read(chars);
                writer.write(chars, 0, count);
            }
        }
        return writer;
    }
}
