package com.javarush.task.task27.task2708;

import java.util.Set;

/* 
Убираем deadLock используя открытые вызовы
*/
public class Solution {
    public static void main(String[] args) {
        final long deadLineTime = System.currentTimeMillis() + 5000;

        final RealEstate realEstate = new RealEstate();
        Set<Apartment> allApartments = realEstate.getAllApartments();

        final Apartment[] apartments =
                allApartments.toArray(new Apartment[allApartments.size()]);

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 10; i1++) {
                    realEstate.revalidate();
                }
            }, "RealEstateThread" + i).start();

            new Thread(() -> {
                for (int i2 = 0; i2 < apartments.length; i2++) {
                    apartments[i2].revalidate(true);
                }
            }, "ApartmentThread" + i).start();
        }
        //Конец цикла

        Thread daemonThread = new Thread(() -> {
            while (System.currentTimeMillis() < deadLineTime)
                try {
                    Thread.sleep(2);
                } catch (InterruptedException ignored) {
                }
            System.out.println("The dead lock occurred");
        });

        daemonThread.setDaemon(true);
        daemonThread.start();
    }
}