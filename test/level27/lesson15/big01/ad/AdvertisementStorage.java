package com.javarush.test.level27.lesson15.big01.ad;

import java.util.ArrayList;
import java.util.List;

class AdvertisementStorage {
    private static volatile AdvertisementStorage ourInstance = new AdvertisementStorage();
    private final List<Advertisement> videos = new ArrayList();

    public static AdvertisementStorage getInstance() {
        return ourInstance;
    }

    public List<Advertisement> list(){
        return videos;
    }

    public void add(Advertisement advertisement){
        videos.add(advertisement);
    }

    private AdvertisementStorage() {
        Object someContent = new Object();

//        add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60)); // 3 min
//        add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60)); //15 min
//        add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));   //10 min
//        add(new Advertisement(someContent, "четвертое видео", 300, 3, 10 * 60));   //10 min

        add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60));
        add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60));
        add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));
        add(new Advertisement(someContent, "4", 2000, 3, 20 * 60));

    }
}
