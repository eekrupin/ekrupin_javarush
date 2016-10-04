package com.javarush.test.level32.lesson15.big01;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Евгений on 03.10.2016.
 */
public class HTMLFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) return true;

        String name = file.getName().toLowerCase();
        return name.endsWith(".html")||name.endsWith(".htm");
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
