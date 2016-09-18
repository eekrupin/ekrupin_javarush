package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);
    }

    private static ResourceBundle res_deposit = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    public static String readString() throws InterruptOperationException {
        String s = "";
        try {
            s = reader.readLine();
        } catch (IOException e) {}
        if (s.toUpperCase().equals(Operation.EXIT.toString())){
            writeMessage(res.getString("the.end"));
            throw new InterruptOperationException();
        }
        return s;
    }

    public static void printExitMessage()
    {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String currencyCode;
        while (true){
            writeMessage(res.getString("choose.currency.code"));
            currencyCode = readString();
            if (currencyCode.length()!=3) writeMessage("Валюта должна содержать 3 символа");
            else break;
        }
        return currencyCode.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true){
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            String[] s = readString().split(" ");
            if (s.length!=2)  writeMessage("Должен быть только 1 пробел");
            else {
                if (s[0].matches("\\d+") && s[1].matches("\\d+")) return s;
                else writeMessage(res_deposit.getString("invalid.data"));
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {

        while (true){
            //writeMessage("Выберите операцию: 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT: ");
            writeMessage(res.getString("choose.operation"));
            writeMessage(String.format("1 - %s, 2 - %s, 3 - %s, 4 - %s", res.getString("operation.INFO"), res.getString("operation.DEPOSIT"), res.getString("operation.WITHDRAW"), res.getString("operation.EXIT")));
            try {
                Operation operation = Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));

                return operation;
            }
            catch (InterruptOperationException e){
                throw e;
            }
            catch (Exception e){
                writeMessage(res.getString("invalid.data"));
            }

        }
    }


}
