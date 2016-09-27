package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Евгений on 27.09.2016.
 */
public class BotClient extends Client {
    private static volatile AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    public class BotSocketThread extends SocketThread{

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String command;
            String senderName = "";
            if (message.contains(": ")) {
                String[] array = message.split(": ");
                senderName = array[0];
                command = array[1];
            }
            else command = message;

            SimpleDateFormat format = null;
            if ("дата".equalsIgnoreCase(command)) format = new SimpleDateFormat("d.MM.YYYY");
            else if ("день".equalsIgnoreCase(command)) format = new SimpleDateFormat("d");
            else if ("месяц".equalsIgnoreCase(command)) format = new SimpleDateFormat("MMMM");
            else if ("год".equalsIgnoreCase(command)) format = new SimpleDateFormat("YYYY");
            else if ("время".equalsIgnoreCase(command)) format = new SimpleDateFormat("H:mm:ss");
            else if ("час".equalsIgnoreCase(command)) format = new SimpleDateFormat("H");
            else if ("минуты".equalsIgnoreCase(command)) format = new SimpleDateFormat("m");
            else if ("секунды".equalsIgnoreCase(command)) format = new SimpleDateFormat("s");

            if (format != null) sendTextMessage("Информация для " + senderName + ": " + format.format(Calendar.getInstance().getTime()));

        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + count.addAndGet(1);
    }
}
