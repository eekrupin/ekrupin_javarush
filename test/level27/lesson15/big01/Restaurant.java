package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private final static int ORDER_CREATING_INTERVAL = 100;
    private final static LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    public static int getOrderCreatingInterval() {
        return ORDER_CREATING_INTERVAL;
    }

    public static void main(String[] args) {

    //РАБОЧАЯ ЧАСТЬ
        Cook cook1 = new Cook("Amigo");
        cook1.setQueue(queue);
        Cook cook2 = new Cook("Diego");
        cook2.setQueue(queue);

        Waitor waitor = new Waitor();
        cook1.addObserver(waitor);
        cook2.addObserver(waitor);

        Thread threadCook1 = new Thread(cook1);
        Thread threadCook2 = new Thread(cook2);
        threadCook1.start();
        threadCook2.start();

        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i <=5 ; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(queue);
            tablets.add(tablet);
        }

        tablets.get(0).createOrder();

       /* Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, Restaurant.ORDER_CREATING_INTERVAL));
        thread.start();

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {}
        thread.interrupt();*/

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {}

        threadCook1.interrupt();
        threadCook2.interrupt();

        try {
            threadCook1.join();
        }catch (InterruptedException  e){}

        try {
            threadCook2.join();
        }catch (InterruptedException  e){}
    //РАБОЧАЯ ЧАСТЬ\

//        Tablet tablet = new Tablet(5);
//        Cook cook = new Cook("Amigo");
//        Waitor waitor = new Waitor();
//        cook.addObserver(waitor);
//        tablet.addObserver(cook);
//        tablet.createOrder();

//        List<Tablet> tablets = new ArrayList<>();
//        for (int i = 1; i <=30 ; i++) {
//            tablets.add(new Tablet(i));
//        }
//
//        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, Restaurant.ORDER_CREATING_INTERVAL));
//        thread.start();
//
//        try {
//            Thread.currentThread().sleep(500);
//        } catch (InterruptedException e) {}
//        thread.interrupt();

        // First order
//        Tablet tablet = new Tablet(5);
//        Cook cook = new Cook("Ivanov");
//        tablet.addObserver(cook);
//        Waitor waitor = new Waitor();
//        cook.addObserver(waitor);
//        tablet.createOrder();
//        // Second Order
//        Tablet tablet2 = new Tablet(5);
//        Cook cook2 = new Cook("Petrov");
//        tablet2.addObserver(cook2);
//        Waitor waitor2 = new Waitor();
//        cook2.addObserver(waitor2);
//        tablet2.createOrder();
//        // Third order
//        Tablet tablet3 = new Tablet(5);
//        Cook cook3 = new Cook("Ivanov");
//        tablet3.addObserver(cook3);
//        Waitor waitor3 = new Waitor();
//        cook3.addObserver(waitor2);
//        tablet3.createOrder();
//        // Fourth order
//        Tablet tablet4 = new Tablet(5);
//        Cook cook4 = new Cook("Ivanov");
//        tablet4.addObserver(cook4);
//        Waitor waitor4 = new Waitor();
//        cook4.addObserver(waitor4);
//        tablet4.createOrder();
//        // Fifth order
//        Tablet tablet5 = new Tablet(5);
//        Cook cook5 = new Cook("Petrov");
//        tablet5.addObserver(cook5);
//        Waitor waitor5 = new Waitor();
//        cook5.addObserver(waitor5);
//        tablet5.createOrder();

        //RandomOrderGeneratorTask task = new RandomOrderGeneratorTask();

        DirectorTablet directorTablet=new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();

    }
}
