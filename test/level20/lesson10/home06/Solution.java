package com.javarush.test.level20.lesson10.home06;

import java.io.*;

/* Запрет сериализации
Запретите сериализацию класса SubSolution используя NotSerializableException.
Сигнатуры классов менять нельзя
*/
public class Solution implements Serializable {
    public static class SubSolution extends Solution {

        private void readObject(ObjectInputStream o) throws IOException, InterruptedIOException{
            throw new NotSerializableException();
        }

        private void writeObject(ObjectOutputStream o) throws IOException{
            throw new NotSerializableException();
        }

    }
}
