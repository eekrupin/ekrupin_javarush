package com.javarush.test.level16.lesson13.home01;

/* Thread.currentThread - всегда возвращает текущую нить
1. В методе printMsg присвой переменной t текущую нить.
2. В методе printMsg после всех действий поставь задержку в 1 миллисекунду.
*/

import java.util.concurrent.TimeUnit;

public class Solution {
    static int count = 5;

    public static void main(String[] args) throws InterruptedException {
        NameOfDefferentThreads tt = new NameOfDefferentThreads();
        tt.start();
        for (int i = 0; i < count; i++) {
            tt.printMsg();
        }
    }

    public static class NameOfDefferentThreads extends Thread {
        public void run() {
            for (int i = 0; i < count; i++) {
                try {
                    printMsg();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void printMsg() throws InterruptedException {
            Thread t = Thread.currentThread();
            String name = t.getName();
            System.out.println("name=" + name);
            TimeUnit.MILLISECONDS.sleep(1);
            //Thread.sleep(1);
        }
    }
}
