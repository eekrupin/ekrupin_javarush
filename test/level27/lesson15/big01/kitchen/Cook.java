package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable{
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public Cook(String name) {
        this.name = name;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order){
        busy = true;
        int cookingTime = order.getTotalCookingTime();
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + cookingTime + "min");

        try {Thread.currentThread().sleep(cookingTime*10);} catch (InterruptedException e) {}

        CookedOrderEventDataRow event = new CookedOrderEventDataRow(order.getTablet().toString(), name, cookingTime*60, order.getDishes());
        StatisticEventManager.getInstance().register(event);

        setChanged();
        notifyObservers(order);
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    @Override
    public void run() {
        try{
            while (!Thread.currentThread().isInterrupted()){
                if (!isBusy()){
                    while (!Thread.currentThread().isInterrupted()){
                        if (!queue.isEmpty()){
                            startCookingOrder(queue.take());
                            break;
                        }
                        Thread.sleep(10);
                    }
                }
            }
        }
        catch (InterruptedException e){

        }
    }
}
