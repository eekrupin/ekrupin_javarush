package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AdvertisementManager {
    private final AdvertisementStorage storage;
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
        storage = AdvertisementStorage.getInstance();
    }

    public void processVideos() {
        ShowListWithParams showListWithParams = getShowListWithParams((ArrayList<Advertisement>) storage.list(), timeSeconds, 0);
        ArrayList<Advertisement> showList = showListWithParams.getShowList();

        if (showList.size()==0) throw new NoVideoAvailableException();

        Collections.sort(showList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int res = (int)(o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
                if (res==0){
                    Double costTime1 = o1.getAmountPerOneDisplaying()/Double.valueOf(o1.getDuration());
                    Double costTime2 = o2.getAmountPerOneDisplaying()/Double.valueOf(o2.getDuration());
                    res = (int)(costTime1*1000)-(int)(costTime2*1000);
                }
                return res;
            }
        });

        long totalAmount = 0;
        int totalDuration = 0;

        Double costTime;
        int costSecond;
        for (Advertisement ad : showList) {
            ad.revalidate();
            costTime = ad.getAmountPerOneDisplaying()/Double.valueOf(ad.getDuration());
            costSecond = (int)(costTime*1000);
            ConsoleHelper.writeMessage(String.format("%s is displaying... %s, %s", ad.getName(), ad.getAmountPerOneDisplaying(), costSecond));
            totalAmount+=ad.getAmountPerOneDisplaying();
            totalDuration+=ad.getDuration();
        }

        VideoSelectedEventDataRow event = new VideoSelectedEventDataRow(showList, totalAmount, totalDuration);
        StatisticEventManager.getInstance().register(event);

    }

    private ShowListWithParams getShowListWithParams(ArrayList<Advertisement> allVideos, int timeSeconds, int startIdx) {
        ArrayList<Advertisement> showList = new ArrayList<>();
        int totalTime = 0;
        int totalCost = 0;
        for (int i = startIdx; i <allVideos.size() ; i++) {
            Advertisement ad = allVideos.get(i);
            if (ad.getHits()<=0) continue;
            if (totalTime+ad.getDuration()<=timeSeconds){
                showList.add(ad);
                totalTime+=ad.getDuration();
                totalCost+=ad.getAmountPerOneDisplaying();
            }
            if (totalTime==timeSeconds) break;
        }
        ShowListWithParams showListWithParams = new ShowListWithParams(totalTime, totalCost, showList);

        if (startIdx<allVideos.size()){
            ShowListWithParams alternativeShowListWithParams = getShowListWithParams(allVideos, timeSeconds, startIdx+1);
            if (showListWithParams.compareTo(alternativeShowListWithParams)<0) showListWithParams = alternativeShowListWithParams;
        }

        return showListWithParams;
    }

    private class ShowListWithParams implements Comparable{
        private int totalTime;
        private int totalCost;
        private ArrayList<Advertisement> showList;

        public ShowListWithParams(int totalTime, int totalCost, ArrayList<Advertisement> showList) {
            this.totalTime = totalTime;
            this.totalCost = totalCost;
            this.showList = showList;
        }

        public int getTotalTime() {
            return totalTime;
        }

        public int getTotalCost() {
            return totalCost;
        }

        public ArrayList<Advertisement> getShowList() {
            return showList;
        }

        @Override
        public int compareTo(Object o) {

            ShowListWithParams compare = (ShowListWithParams)o;
            if (totalCost>compare.getTotalCost()) return 1;
            else if (totalCost==compare.getTotalCost()) {
                if (totalTime>compare.getTotalTime()) return 1;
                else if (totalTime==compare.totalTime) {
                    if (showList.size()<compare.getShowList().size()) return 1;
                    else if (showList.size()>compare.getShowList().size()) return -1;
                    else return 0;
                }
                else return -1;
            }
            return -1;
        }
    }

}
