package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet{
    private final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue;

    public Tablet(int number) {
        this.number = number;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public void createOrder(){
        try
        {
            Order order = new Order(this);
            processOrder(order);
        }
        catch (IOException e){
            logger.log(Level.SEVERE, "Console is unavailable.");
            return;
        }
    }

    private void processOrder(Order order) {
        ConsoleHelper.writeMessage(order.toString());
        if (!order.isEmpty()) {
            AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime()*60);
            queue.add(order);
            try {advertisementManager.processVideos();}
            catch (NoVideoAvailableException e) {logger.log(Level.INFO, "No video is available for the order " + order);}
        }
    }

    public void createTestOrder(){
        try {
            TestOrder testOrder = new TestOrder(this);
            processOrder(testOrder);
        }
        catch (IOException e){
            logger.log(Level.SEVERE, "Console is unavailable.");
            return;
        }

    }

    public String toString() {
        return "Tablet{number=" + number + "}";
    }


}
