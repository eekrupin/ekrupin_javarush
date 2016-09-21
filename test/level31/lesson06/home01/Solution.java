package com.javarush.test.level31.lesson06.home01;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {
    private static Map<ZipEntry, byte[]> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        String strPathSource = args[0];
        String strPathZip = args[1];

        File fileZip = new File(strPathZip);
        putToMap(fileZip);

        File fileSource = new File(strPathSource);
        putToZip(fileZip, fileSource);
    }

    private static void putToZip(File fileZip, File fileSource) {
        ZipEntry newEntry = new ZipEntry("new/"+fileSource.getName());
        String newEntryName = newEntry.getName();
        try(ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(fileZip))) {
            for (Map.Entry<ZipEntry, byte[]> entry : map.entrySet()) {
                String name = entry.getKey().getName();
                if (!name.equals(newEntryName)) {
                    zipOut.putNextEntry(new ZipEntry(name));
                    zipOut.write(entry.getValue());
                }
            }
            ZipEntry entry = newEntry;
            zipOut.putNextEntry(entry);
            Files.copy(fileSource.toPath(), zipOut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void putToMap(File File) {
        try(ZipInputStream zipInput = new ZipInputStream(new FileInputStream(File))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInput.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] cache = new byte[1024];
                int count;
                while ((count = zipInput.read(cache)) != -1) {
                    byteArrayOutputStream.write(cache, 0, count);
                }
                byte[] bytes = byteArrayOutputStream.toByteArray();
                map.put(zipEntry, bytes);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
