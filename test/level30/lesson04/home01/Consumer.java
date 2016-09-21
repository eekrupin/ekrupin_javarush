package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by ekrupin on 21.09.2016.
 */
public class Consumer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(String.format("Processing %s", queue.take().toString()));
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}
