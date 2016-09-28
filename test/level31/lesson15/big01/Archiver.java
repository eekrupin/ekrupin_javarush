package com.javarush.test.level31.lesson15.big01;

import com.javarush.test.level31.lesson15.big01.command.ExitCommand;
import com.javarush.test.level31.lesson15.big01.exception.WrongZipFileException;

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

        Operation operation;
        while (true) {

            try {
                operation = askOperation();
                if (operation==Operation.EXIT) break;
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
               ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            }
            catch (Exception e){
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }
        }

        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute();

    }

    public static Operation askOperation() throws IOException{
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Выберите операцию:");
        ConsoleHelper.writeMessage(String.format("\t %d - упаковать файлы в архив", Operation.CREATE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - добавить файл в архив", Operation.ADD.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - удалить файл из архива", Operation.REMOVE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - распаковать архив", Operation.EXTRACT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - просмотреть содержимое архива", Operation.CONTENT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - выход", Operation.EXIT.ordinal()));

        return Operation.values()[ConsoleHelper.readInt()];
    }

}
