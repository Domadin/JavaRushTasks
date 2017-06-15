package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    private SimpleDateFormat format = new SimpleDateFormat("dd-MMM-YYYY", Locale.ENGLISH);

    public void printAdvertisementProfit() { //какую сумму заработали на рекламе, сгруппировать по дням;
        Map<Date, Long> data = StatisticManager.getInstance().getAdvertisementData();
        BigDecimal totalProfit = BigDecimal.ZERO;
        for (Map.Entry<Date, Long> pair : data.entrySet()) {
            BigDecimal profInRubs = BigDecimal.valueOf(pair.getValue()).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
            ConsoleHelper.writeMessage(format.format(pair.getKey()) + " - " + profInRubs);
            totalProfit = totalProfit.add(profInRubs);
        }
        ConsoleHelper.writeMessage("Total - " + totalProfit);

    }

    public void printCookWorkloading() { //загрузка (рабочее время) повара, сгруппировать по дням;
        Map<Date, TreeMap<String, Integer>> data = StatisticManager.getInstance().getCookData();
        for (Map.Entry<Date, TreeMap<String, Integer>> outPair : data.entrySet()) {
            String date = format.format(outPair.getKey());
            ConsoleHelper.writeMessage(date);
            for (Map.Entry<String, Integer> inPair : outPair.getValue().entrySet()) {
                Integer timeInMins = (int) Math.ceil(inPair.getValue() / 60.0);
                ConsoleHelper.writeMessage(inPair.getKey() + " - " + timeInMins + " min");
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet() { //список активных роликов и оставшееся количество показов по каждому;
        List<Advertisement> activeVideos = StatisticAdvertisementManager.getInstance().getActiveVideos();
        activeVideos.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement ad : activeVideos) {
            ConsoleHelper.writeMessage(ad.getName() + " - " + ad.getHits());
        }
    }

    public void printArchivedVideoSet() { //список неактивных роликов (с оставшемся количеством показов равным нулю).
        List<Advertisement> inactiveVideos = StatisticAdvertisementManager.getInstance().getInactiveVideos();
        inactiveVideos.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement ad : inactiveVideos) {
            ConsoleHelper.writeMessage(ad.getName());
        }
    }
}
