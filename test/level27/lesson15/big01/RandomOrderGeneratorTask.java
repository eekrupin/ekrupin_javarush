package com.javarush.test.level27.lesson15.big01;

import java.util.List;

/**
 * Created by Евгений on 17.09.2016.
 */
public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        if (tablets.isEmpty()) return;
        while (!Thread.currentThread().isInterrupted()){
            int ind = (int)(Math.random()*tablets.size());
            Tablet tablet = tablets.get(ind);
            tablet.createTestOrder();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                return;
            }
        }

    }
}
