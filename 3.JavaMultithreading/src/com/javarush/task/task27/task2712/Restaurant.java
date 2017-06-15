package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) throws InterruptedException {
        DirectorTablet directorTablet = new DirectorTablet();
        Waiter waiter = new Waiter();
        Cook cook = new Cook("Amigo");
        cook.addObserver(waiter);
        cook.setQueue(orderQueue);
        Thread cook1Thread = new Thread(cook);
        cook1Thread.setDaemon(true);
        cook1Thread.start();

        Cook cook2 = new Cook("Alex");
        cook2.addObserver(waiter);
        cook2.setQueue(orderQueue);
        Thread cook2Thread = new Thread(cook2);
        cook2Thread.setDaemon(true);
        cook2Thread.start();

        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            tablets.add(tablet);
        }

        RandomOrderGeneratorTask randomOrderGeneratorTask = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(randomOrderGeneratorTask);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();



/*        tablet.createOrder();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        tablet.createOrder();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        tablet.createOrder();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();*/
    }
}
