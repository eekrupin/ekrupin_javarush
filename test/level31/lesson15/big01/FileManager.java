package com.javarush.test.level31.lesson15.big01;

import com.sun.xml.internal.bind.v2.TODO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Евгений on 28.09.2016.
 */
public class FileManager {

    private Path rootPath;
    private List<Path> fileList;

    FileManager(Path rootPath) throws IOException {

        this.rootPath = rootPath;
        collectFileList(rootPath);
    }

    public List<Path> getFileList() {
        return fileList;
    }

    private void collectFileList(Path path) throws IOException{
        if (Files.isRegularFile(path)) fileList.add(rootPath.relativize(path));
        else if (Files.isDirectory(path)) ; //TODO
    }

}
