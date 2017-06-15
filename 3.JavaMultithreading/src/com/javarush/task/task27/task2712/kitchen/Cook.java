package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public boolean isBusy() {
        return busy;
    }

    public Cook(String name) {
        this.name = name;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order) throws InterruptedException {
        busy = true;
        int cookingTime = order.getTotalCookingTime();
        ConsoleHelper.writeMessage(String.format("Start cooking - %s, cooking time %smin", order, cookingTime));
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes()));
        Thread.sleep(cookingTime * 10);
        busy = false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!queue.isEmpty()) {
                    if (!isBusy()) {
                        startCookingOrder(queue.poll());
                    }
                }
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
