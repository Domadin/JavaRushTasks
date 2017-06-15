package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class AdvertisementManager {   //у каждого планшета собственный объект менеджера, который будет подбирать оптимальный набор роликов и их последовательность для каждого заказа
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        int maxTime = timeSeconds; //Максимум времени на показ
        List<Advertisement> inputStorage = new ArrayList<>(storage.list());
        inputStorage.removeIf(ad -> ad.getHits() < 1 || ad.getDuration() > maxTime);

        HashSet<ArrayList<Advertisement>> adResult = new HashSet<>();
        for (int size = 1; size <= inputStorage.size(); size++) {
            Advertisement[] tempData = new Advertisement[size];
            HashSet<ArrayList<Advertisement>> tempSet = new HashSet<>();
            tempSet = combineAds(inputStorage, size, 0, tempData, 0, tempSet, maxTime);
            adResult.addAll(tempSet);
        }

        if (adResult.isEmpty()) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(maxTime));
            throw new NoVideoAvailableException();
        }

        ArrayList<Advertisement> resultAds = Collections.max(adResult, (o1, o2) -> {
            int costCompare = Long.compare(getCost(o1), getCost(o2));
            if (costCompare != 0) return costCompare;
            int timeCompare = Integer.compare(getDuration(o1), getDuration(o2));
            if (timeCompare != 0) return timeCompare;
            return Integer.compare(o2.size(), o1.size());
        });

        showAds(resultAds);
    }

    private HashSet<ArrayList<Advertisement>> combineAds(List<Advertisement> inputStorage,
                                                         int size, int startId, Advertisement[] tempData,
                                                         int numElem, HashSet<ArrayList<Advertisement>> tempSet, int maxTime) {
        if (numElem == size) {
            int time = 0;
            ArrayList<Advertisement> currList = new ArrayList<>();
            for (int i = 0; i < tempData.length; i++) {
                Advertisement currAd = tempData[i];
                time += currAd.getDuration();
                if (time > maxTime) break;
                currList.add(currAd);
            }
            if (!currList.isEmpty()) tempSet.add(currList);
            return tempSet;
        }

        for (int i = startId; i < inputStorage.size(); i++) {
            tempData[numElem++] = inputStorage.get(i);
            combineAds(inputStorage, size, ++startId, tempData, numElem, tempSet, maxTime);
            --numElem;
        }
        return tempSet;
    }

    private long getCost(ArrayList<Advertisement> ads) {
        long cost = 0;
        for (Advertisement ad : ads) {
            cost += ad.getAmountPerOneDisplaying();
        }
        return cost;
    }

    private int getDuration(ArrayList<Advertisement> ads) {
        int duration = 0;
        for (Advertisement ad : ads) {
            duration += ad.getDuration();
        }
        return duration;
    }

    private void showAds(ArrayList<Advertisement> advertisements) {
        Collections.sort(advertisements);
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(advertisements, getCost(advertisements), getDuration(advertisements)));
        for (Advertisement ad : advertisements) {
            ConsoleHelper.writeMessage(ad.toString());
            ad.revalidate();
        }
    }
}
