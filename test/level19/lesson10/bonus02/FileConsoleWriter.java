package com.javarush.test.level19.lesson10.bonus02;

/* Свой FileWriter
Реализовать логику FileConsoleWriter
Должен наследоваться от FileWriter
При записи данных в файл, должен дублировать эти данные на консоль
*/

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;


public class FileConsoleWriter extends FileWriter {

    private StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException
    {
        FileConsoleWriter writer = new FileConsoleWriter("E:\\Java\\file.txt");
        char[] ch = new char[]{'a','b','c'};
        String str = "StringStringString";
        writer.write(str);
        writer.write(str, 2,2);
        writer.write(6456);
        writer.write(System.getProperty("line.separator"));
        writer.write(ch);
        writer.write(ch,1,2);
        writer.write(str,0,3);
        writer.close();
    }

    public FileConsoleWriter(String fileName) throws IOException {super(fileName);}
    public FileConsoleWriter(String fileName, boolean append) throws IOException {super(fileName, append);}
    public FileConsoleWriter(File file) throws IOException {super(file);}
    public FileConsoleWriter(File file, boolean append) throws IOException {super(file, append);}
    public FileConsoleWriter(FileDescriptor fd) {super(fd);}

    @Override
    public void close() throws IOException {
        super.close();
        System.out.println(res.toString());
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c);
        res.append((char)c);
        //System.out.print(c);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        super.write(cbuf, off, len);
        StringBuilder sb = new StringBuilder();
        for (int i = off; i < off+len; i++) sb.append(cbuf[i]);
        res.append(sb.toString());
        //System.out.print(sb.toString());
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        super.write(str, off, len);
        res.append(str.substring(off, off + len));
        //System.out.print();
    }

}
