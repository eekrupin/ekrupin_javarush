package com.javarush.test.level31.lesson08.home01;

import java.io.File;

/* Null Object Pattern
Почитайте на вики про паттерн "Null Object"
Используйте Files, чтобы в конструкторе класса Solution правильно инициализировать поле fileData объектом ConcreteFileData.
Если возникли какие-то проблемы со чтением файла по пути pathToFile, то инициализируйте поле объектом NullFileData.
*/
public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) {
        File file = new File(pathToFile);
        try {
            fileData = new ConcreteFileData(file.isHidden(), file.canExecute(), file.isDirectory(), file.canWrite());
        }
        catch (Exception e) {
            fileData = new NullFileData(e);
        }

    }

    public FileData getFileData() {
        return fileData;
    }
}
