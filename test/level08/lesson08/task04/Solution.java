package com.javarush.test.level08.lesson08.task04;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{
    public static void main(String[] args) {
        HashMap<String, Date> map = createMap();
        removeAllSummerPeople(map);
        for (Map.Entry<String, Date>pair:map.entrySet()) {
            System.out.println(pair.getKey() + " - " + pair.getValue());
        }

    }

    public static HashMap<String, Date> createMap()
    {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone1", new Date("JUNE 1 1980"));
        map.put("Stallone2", new Date("JUNE 6 1995"));
        map.put("Stallone3", new Date("MAY 1 1980"));
        map.put("Stallone4", new Date("SEPTEMBER 1 1980"));
        map.put("Stallone5", new Date("MAY 5 1982"));
        map.put("Stallone6", new Date("JUNE 11 1980"));
        map.put("Stallone7", new Date("JUNE 10 1995"));
        map.put("Stallone8", new Date("MAY 9 1980"));
        map.put("Stallone9", new Date("SEPTEMBER 8 1980"));
        map.put("Stallone10", new Date("MAY 7 1982"));

        return map;

    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {

        ArrayList<String> arr = new ArrayList();
        for (Map.Entry<String, Date>pair:map.entrySet()) {
            int month = pair.getValue().getMonth();
            if(month>4 && month<8) arr.add(pair.getKey());
        }

        for (String key:arr) {
            map.remove(key);
        }

    }
}
