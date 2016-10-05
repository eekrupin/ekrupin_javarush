package com.javarush.test.level33.lesson15.big01.strategies;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {

    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("tmp", null);
        }
        catch (IOException e) {
            //ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize(){
        return path.toFile().length();
    }

    public void putEntry(Entry entry){

        try {
            FileOutputStream fos = new FileOutputStream(path.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(entry);
            fos.close();
            oos.close();
        } catch (IOException e) {
            //ExceptionHandler.log(e);
        }

    }

    public Entry getEntry(){

        Entry entry = null;
        if (path.toFile().length()>0) {
            try {
                FileInputStream fis = new FileInputStream(path.toFile());
                ObjectInputStream ois = new ObjectInputStream(fis);
                entry = (Entry) ois.readObject();
                fis.close();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                //ExceptionHandler.log(e);
            }
        }

        return entry;
    }

    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {
            //ExceptionHandler.log(e);
        }
    }

}
