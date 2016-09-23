package com.javarush.test.level30.lesson04.home01;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.util.concurrent.TransferQueue;

/**
 * Created by ekrupin on 21.09.2016.
 */
public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        for (int i = 1; i <=9 ; i++) {

            System.out.format("Элемент 'ShareItem-%d' добавлен%n", i);
            ShareItem shareItem = new ShareItem("ShareItem-" + i, i);
            queue.offer(shareItem);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
            if (queue.hasWaitingConsumer()) System.out.println("Consumer в ожидании!");
        }


    }
}
