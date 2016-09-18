package com.javarush.test.level26.lesson10.home02;

import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable{
    protected ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }


    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();

        try {
        int i = 0;
        while(!currentThread.isInterrupted()){
            i++;
            String str = String.format("Some text for %d", i);
            map.put(String.valueOf(i), str);
            Thread.sleep(500);
        }
        } catch (InterruptedException e) {
            System.out.println(String.format("%s thread was terminated", currentThread.getName()));
        }
    }
}
