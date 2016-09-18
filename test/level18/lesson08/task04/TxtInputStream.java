package com.javarush.test.level18.lesson08.task04;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* UnsupportedFileName
Измените класс TxtInputStream так, чтобы он работал только с txt-файлами (*.txt)
Например, first.txt или name.1.part3.txt
Если передан не txt-файл, например, file.txt.exe, то конструктор должен выбрасывать исключение UnsupportedFileNameException
*/

public class TxtInputStream extends FileInputStream {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedFileNameException {
        TxtInputStream txt = new TxtInputStream("C:\\java\\22.22.txt");
    }

    public TxtInputStream(String fileName) throws UnsupportedFileNameException, FileNotFoundException {
        super(fileName);
        if (!isTxt(fileName)) throw new UnsupportedFileNameException();
    }

    public static boolean isTxt(String s){
        boolean result = false;
        int begin = s.lastIndexOf('.');
        if (begin > 0){
            result = s.substring(begin+1).toLowerCase().equals("txt");
        }

        return result;
    }

}

