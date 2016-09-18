package com.javarush.test.level27.lesson15.big01.ad;

import java.util.TreeMap;

/**
 * Created by Евгений on 17.09.2016.
 */
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private static AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public TreeMap<String, Integer> getTreeMapOfVideos(boolean hasHints){
        TreeMap<String, Integer> tree = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Advertisement ad : advertisementStorage.list()) {
            if (hasHints && ad.getHits()>0) tree.put(ad.getName(), ad.getHits());
            else if (!hasHints && ad.getHits()<=0) tree.put(ad.getName(), 0);
        }

        return tree;
    }

}
