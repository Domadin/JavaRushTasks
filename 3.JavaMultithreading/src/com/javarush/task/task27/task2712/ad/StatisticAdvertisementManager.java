package com.javarush.task.task27.task2712.ad;

import java.util.*;

public class StatisticAdvertisementManager {
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public List<Advertisement> getActiveVideos() {
        List<Advertisement> result = new ArrayList<>();
        List<Advertisement> adStorage = storage.list();
        for (Advertisement ad : adStorage) {
            if (ad.getHits() > 0) {
                result.add(ad);
            }
        }
        return result;
    }

    public List<Advertisement> getInactiveVideos() {
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement ad : storage.list()) {
            if (ad.getHits() <= 0) {
                result.add(ad);
            }
        }
        return result;
    }
}
