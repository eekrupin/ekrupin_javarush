package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.Operation;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ekrupin on 14.09.2016.
 */
public final class CommandExecutor
{
    private static Map<Operation, Command> commands = new HashMap<Operation, Command>();
    static
    {
        commands.put(Operation.LOGIN, new LoginCommand());
        commands.put(Operation.EXIT, new ExitCommand());
        commands.put(Operation.INFO, new InfoCommand());
        commands.put(Operation.DEPOSIT, new DepositCommand());
        commands.put(Operation.WITHDRAW, new WithdrawCommand());
    }
    private CommandExecutor(){}
    public static final void execute(Operation operation) throws InterruptOperationException
    {
        commands.get(operation).execute();
    }
}