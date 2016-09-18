package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.StatisticAdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class DirectorTablet {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);


    public void printAdvertisementProfit(){
        TreeMap<Date, Long> map = StatisticEventManager.getInstance().getDataSelectedVideo();
        long total = 0;
        for (Map.Entry<Date, Long> entry : map.entrySet()) {
            System.out.println(String.format(Locale.ENGLISH, "%s - %.2f", simpleDateFormat.format(entry.getKey()), entry.getValue()*1.0d/100));
            total+=entry.getValue();
        }
        System.out.println(String.format(Locale.ENGLISH, "Total - %.2f", total*1.0d/100));
    }

    public void printCookWorkloading(){
        TreeMap<Date, TreeMap<String, Long>> dataCookWorkloading = StatisticEventManager.getInstance().getDataCookWorkloading();
        for (Map.Entry<Date, TreeMap<String, Long>> dateCooks : dataCookWorkloading.entrySet()) {
            System.out.println(simpleDateFormat.format(dateCooks.getKey()));
            for (Map.Entry<String, Long> cookTime : dateCooks.getValue().entrySet()) {
                if (cookTime.getValue()>0) {
                    int workedMinutes = (int)Math.ceil(cookTime.getValue()*1.0d/60);
                    System.out.println(String.format(Locale.ENGLISH, "%s - %s min", cookTime.getKey(), workedMinutes));
                }
            }
            System.out.println();
        }


    }

    public void printActiveVideoSet(){
        TreeMap<String, Integer> tree = StatisticAdvertisementManager.getInstance().getTreeMapOfVideos(true);
        for (Map.Entry<String, Integer> entry : tree.entrySet()) {
            System.out.println(String.format(Locale.ENGLISH, "%s - %s", entry.getKey(), entry.getValue()));
        }
    }

    public void printArchivedVideoSet(){
        TreeMap<String, Integer> tree = StatisticAdvertisementManager.getInstance().getTreeMapOfVideos(false);
        for (Map.Entry<String, Integer> entry : tree.entrySet()) {
            System.out.println(String.format(Locale.ENGLISH, "%s", entry.getKey()));
        }
    }

}
