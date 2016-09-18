package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by ekrupin on 14.09.2016.
 */
class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");
    public void execute() {
        if (CurrencyManipulatorFactory.hasMoney()) {
            Collection<CurrencyManipulator> manipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
            ConsoleHelper.writeMessage(res.getString("before"));
            for (CurrencyManipulator manipulator : manipulators) {
                int totalSum = manipulator.getTotalAmount();
                ConsoleHelper.writeMessage(String.format("%s - %d", manipulator.getCurrencyCode(), totalSum));
            }
        }
        else ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}