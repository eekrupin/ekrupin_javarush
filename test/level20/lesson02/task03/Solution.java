package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Solution sol = new Solution();
        sol.fillInPropertiesMap();

        FileOutputStream out = new FileOutputStream("D:\\Java\\2.txt");
        sol.save(out);
    }

    public void fillInPropertiesMap() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fn = consoleReader.readLine();
            consoleReader.close();
            load(new FileInputStream(fn));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(OutputStream outputStream) throws Exception {
        Properties prop = new Properties();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            prop.setProperty(entry.getKey(), entry.getValue());
        }
        prop.save(outputStream, null);

    }

    public void load(InputStream inputStream) throws Exception {
        Properties prop = new Properties();
        prop.load(inputStream);

        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            properties.put((String)entry.getKey(), (String)entry.getValue());
        }
    }
}
