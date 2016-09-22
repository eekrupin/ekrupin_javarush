package com.javarush.test.level31.lesson06.bonus01;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length<2) return;
        String resultFileName = args[0];
        int countParts = args.length-1;
        String[] parts = new String[countParts];
        for (int i = 0; i < countParts ; i++) {
            parts[i] = args[i+1];
        }
        Arrays.sort(parts);

        ArrayList<FileInputStream> inputStreams = new ArrayList<>();
        for (String part : parts) {
            inputStreams.add(new FileInputStream(part));
        }

        SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(inputStreams));
        ZipInputStream zipInputStream = new ZipInputStream(sequenceInputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(resultFileName);
        byte[] buffer = new byte[1024*1024];
        while (zipInputStream.getNextEntry()!=null){
            int count;
            while ((count = zipInputStream.read(buffer))!=-1){
                fileOutputStream.write(buffer, 0, count);
            }
        }
        sequenceInputStream.close();
        zipInputStream.close();
        fileOutputStream.close();
    }
}
