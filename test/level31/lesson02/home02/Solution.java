package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new ArrayList<>();
        Stack<File> stack= new Stack<>();
        stack.push(new File(root));
        while (!stack.isEmpty()){
            File file = stack.pop();
            File[] files = file.listFiles();
            for (int i = 0; i < files.length ; i++) {
                if (files[i].isDirectory()) stack.push(files[i]);
                else list.add(files[i].getAbsolutePath());
            }
        }

        return list;

    }

    public static void main(String[] args) throws IOException {
        List<String> fileList = getFileTree("E:\\Java\\JavaRushHomeWork\\JavaRushHomeWork\\src\\com\\javarush\\test\\level31");
        for (String s : fileList) {
            System.out.println(s);
        }
    }

}
