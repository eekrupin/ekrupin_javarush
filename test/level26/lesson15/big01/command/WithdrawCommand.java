package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by ekrupin on 14.09.2016.
 */
class WithdrawCommand implements Command {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int sum;
        while (true){
            ConsoleHelper.writeMessage(res.getString("before"));
            try {
                sum = Integer.parseInt(ConsoleHelper.readString());
                if (sum<=0){
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    continue;
                }
                if (manipulator.isAmountAvailable(sum)) {
                    Map<Integer, Integer> map = manipulator.withdrawAmount(sum);
                    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                        //ConsoleHelper.writeMessage(String.format("\t%d - %d", entry.getKey(), entry.getValue()));
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), entry.getKey(), entry.getValue()));
                    }
                    break;
                }
                else{
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }

            }
            catch (InterruptOperationException e){throw e;}
            catch (NotEnoughMoneyException e){
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }
            catch (ConcurrentModificationException e){

            }
            catch (NumberFormatException e){
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
            }
            catch (Exception e){}
        }

    }
}
