package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticEventManager {
    private static StatisticEventManager ourInstance = new StatisticEventManager();
    private static StatisticEventManager.StatisticStorage statisticStorage = new StatisticStorage();

    public static StatisticEventManager getInstance() {
        return ourInstance;
    }

    private StatisticEventManager() {
    }

    private static class StatisticStorage{
        private Map<EventType, List<EventDataRow>> eventTypeListMap = new HashMap<>();

        public StatisticStorage() {
            for (EventType event : EventType.values()) {
                eventTypeListMap.put(event, new ArrayList<EventDataRow>());
            }
        }

        public List<EventDataRow> getStatisticSelectedVideo(){
            return eventTypeListMap.get(EventType.SELECTED_VIDEOS);
        }

        public List<EventDataRow> getStatisticCookWorkloading(){
            return eventTypeListMap.get(EventType.COOKED_ORDER);
        }

        private void put(EventDataRow data){
            eventTypeListMap.get(data.getType()).add(data);
        }

    }

    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

    public TreeMap<Date, Long> getDataSelectedVideo(){
        TreeMap<Date, Long> map = new TreeMap(Collections.reverseOrder());
        List<EventDataRow> listOfVideo = statisticStorage.getStatisticSelectedVideo();

        for (EventDataRow dataRow : listOfVideo) {
            Date date = getDateAtNight(dataRow.getDate());
            if (!map.containsKey(date)) map.put(date, 0L);
            map.put(date, map.get(date)+((VideoSelectedEventDataRow)dataRow).getAmount());
        }

        return map;
    }

    public TreeMap<Date, TreeMap<String, Long>> getDataCookWorkloading(){
        TreeMap<Date, TreeMap<String, Long>> map = new TreeMap(Collections.reverseOrder());
        List<EventDataRow> listOfCookWorkloading = statisticStorage.getStatisticCookWorkloading();

        for (EventDataRow dataRow : listOfCookWorkloading) {
            CookedOrderEventDataRow data = (CookedOrderEventDataRow) dataRow;
            Date date = getDateAtNight(dataRow.getDate());

            if (!map.containsKey(date)) map.put(date, new TreeMap<String, Long>());
            TreeMap<String, Long> treeOfCooks = map.get(date);

            if (!treeOfCooks.containsKey(data.getCookName())) treeOfCooks.put(data.getCookName(), 0L);
            treeOfCooks.put(data.getCookName(), treeOfCooks.get(data.getCookName())+data.getTime());
        }

        return map;
    }

    private Date getDateAtNight(Date date) {

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();

    }

}
