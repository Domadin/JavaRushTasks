package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {     //хранилище рекламных роликов. Синглтон, т.к. един для всего ресторана.
    private static AdvertisementStorage ourInstance = new AdvertisementStorage();
    private final List<Advertisement> videos = new ArrayList<>();

    public static AdvertisementStorage getInstance() {
        return ourInstance;
    }

    private AdvertisementStorage() {
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 15 * 60)); // 15 min
        videos.add(new Advertisement(someContent, "Second Video", 100, 10, 3 * 60)); //3 min
        videos.add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60)); //10 min
        videos.add(new Advertisement(someContent, "Forth Video", 12, 3, 60)); //1 min
        videos.add(new Advertisement(someContent, "Fifth Video", 8, 2, 60)); //1 min
        videos.add(new Advertisement(someContent, "Sixth Video", 16, 2, 60)); //1 min
        videos.add(new Advertisement(someContent, "Апервое видео", 8, 2, 60)); //1 min
        videos.add(new Advertisement(someContent, "Второе видео", 16, 2, 60)); //1 min
/*        Advertisement ad1 = new Advertisement(someContent, "Четвертое ноль видео", 8, 2, 60);
        Advertisement ad2 = new Advertisement(someContent, "Пятое ноль видео", 8, 2, 60);
        videos.add(ad1); //1 min
        videos.add(ad2); //1 min
        ad1.setHits(0);
        ad2.setHits(0);*/

    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
