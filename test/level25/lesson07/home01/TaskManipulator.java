package com.javarush.test.level25.lesson07.home01;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread th;
    @Override
    public void run() {
        while (!th.isInterrupted()){
            try {
                th.sleep(0);
                System.out.println(th.getName());
                th.sleep(90);
            } catch (InterruptedException e) {
                break;
            }
        }

    }

    @Override
    public void start(String threadName) throws InterruptedException {
        th = new Thread(this, threadName);
        th.start();
    }

    @Override
    public void stop() {
        th.interrupt();
    }
}
