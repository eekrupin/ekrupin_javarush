package com.javarush.test.level22.lesson13.task02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {

        FileInputStream in = new FileInputStream(args[0]);
        FileOutputStream out = new FileOutputStream(args[1]);

        byte[] buffer = new byte[in.available()]; //буфер, равный количеству байтов из файла
        in.read(buffer); //записываем в буфер байты из файла
        String bufStr = new String(buffer, "UTF-8"); //делаем из буфера строку в кодировке
        byte[] buffer2 = bufStr.getBytes("Windows-1251"); //делаем из строки буфер в кодировке</code>
        out.write(buffer2);

        in.close();
        out.close();


    }
}
