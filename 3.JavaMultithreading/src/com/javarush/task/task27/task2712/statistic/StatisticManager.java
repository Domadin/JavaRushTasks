package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.DirectorTablet;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<>());
            }
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

        private List<EventDataRow> getRow(EventType eventType) {
            return storage.get(eventType);
        }
    }

    private StatisticStorage statisticStorage = new StatisticStorage();

    private static StatisticManager ourInstance = new StatisticManager();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public Map<Date, Long> getAdvertisementData() {
        List<EventDataRow> data = statisticStorage.getRow(EventType.SELECTED_VIDEOS);
        Map<Date, Long> resultMap = new TreeMap<>(Collections.reverseOrder());

        for (EventDataRow event : data) {
            VideoSelectedEventDataRow videoEvent = (VideoSelectedEventDataRow) event;
            Long cost = videoEvent.getAmount();
            Date date = roundDate(videoEvent.getDate());
            if (resultMap.containsKey(date)) {
                resultMap.put(date, resultMap.get(date) + cost);
            } else {
                resultMap.put(date, cost);
            }
        }
        return resultMap;
    }

    public Map<Date, TreeMap<String, Integer>> getCookData() {
        List<EventDataRow> data = statisticStorage.getRow(EventType.COOKED_ORDER);
        Map<Date, TreeMap<String, Integer>> resultMap = new TreeMap<>(Collections.reverseOrder());
        TreeMap<String, Integer> cookResult = new TreeMap<>();

        for (EventDataRow event : data) {
            CookedOrderEventDataRow cookEvent = (CookedOrderEventDataRow) event;
            String cookName = cookEvent.getCookName();
            Integer cookingTimeInSec = cookEvent.getTime();
            Date date = roundDate(cookEvent.getDate());

            if (resultMap.containsKey(date)) {
                cookResult = resultMap.get(date);
                if (cookResult.containsKey(cookName)) {
                    cookResult.put(cookName, cookResult.get(cookName) + cookingTimeInSec);
                } else {
                    cookResult.put(cookName, cookingTimeInSec);
                }
            } else {
                cookResult = new TreeMap<>();
                cookResult.put(cookName, cookingTimeInSec);
            }
            resultMap.put(date, cookResult);
        }
        return resultMap;
    }

    private Date roundDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
