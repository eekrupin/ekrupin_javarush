package com.javarush.test.level31.lesson15.big01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Created by Евгений on 22.09.2016.
 */
public class Archiver {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter full path to archive");
        String strPathDest = bufferedReader.readLine();

        ZipFileManager zipFileManager = new ZipFileManager(Paths.get(strPathDest));

        System.out.println("Enter full path what must be archived");
        String strPathSource = bufferedReader.readLine();

        zipFileManager.createZip(Paths.get(strPathSource));

    }
}
