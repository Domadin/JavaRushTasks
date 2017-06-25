package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) { //должен для переданного множества строк возвращать множество идентификаторов
        Set<Long> result = new HashSet<>();
        for (String string : strings) {
            result.add(shortener.getId(string));
        }
        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) { // будет возвращать множество строк, которое соответствует переданному множеству идентификаторов
        Set<String> result = new HashSet<>();
        for (Long key : keys) {
            result.add(shortener.getString(key));
        }
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) { //удет тестировать работу переданной стратегии на определенном количестве элементов elementsNumber.
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> testSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testSet.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date timeBefore = new Date();
        Set<Long> ids = getIds(shortener, testSet);
        Date timeAfter = new Date();
        Helper.printMessage(String.valueOf(timeAfter.getTime() - timeBefore.getTime()));

        timeBefore = new Date();
        Set<String> strings = getStrings(shortener, ids);
        timeAfter = new Date();
        Helper.printMessage(String.valueOf(timeAfter.getTime() - timeBefore.getTime()));

        if (strings.equals(testSet)) Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }
}
