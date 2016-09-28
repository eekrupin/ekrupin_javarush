package com.javarush.test.level31.lesson15.big01;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Евгений on 22.09.2016.
 */
public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception{
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {
            addzipEntry(zipOutputStream, source);
        }
    }

    private void addzipEntry(ZipOutputStream zipOutputStream, Path source) throws IOException {

        ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
        zipOutputStream.putNextEntry(zipEntry);

        try (InputStream inputStream = Files.newInputStream(source)) {
            byte[] buffer = new byte[1024];
            int count = 0;
            while (inputStream.available() > 0) {
                count = inputStream.read(buffer);
                zipOutputStream.write(buffer, 0, count);
            }
        }
        zipOutputStream.closeEntry();
    }

}
