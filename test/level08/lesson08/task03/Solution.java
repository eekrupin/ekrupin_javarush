package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;
import java.util.HashSet;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static void main(String[] args) {
        HashMap<String, String> map = createMap();
        int countName = getCountTheSameFirstName(map, "Никто");
        int countlastName = getCountTheSameLastName(map, "Иванов");

        System.out.println(countName);
        System.out.println(countlastName);
    }

    public static HashMap<String, String> createMap()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("Иванов1", "Сергей");
        map.put("Иванов2", "Петр");
        map.put("Иванов3", "Алекс");
        map.put("Иванов4", "Санек");
        map.put("Иванов5", "Никто");
        map.put("Петров6", "Сергей");
        map.put("Петров7", "Петр");
        map.put("Петров8", "Алекс");
        map.put("Петров9", "Санек");
        map.put("Петров10", "Никто");

        return map;

    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        int count = 0;
        for (String s:map.values()){
            if(s.equals(name)) count++;
        }

        return count;

    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String lastName)
    {
        int count = 0;
        for (String s:map.keySet()){
            if(s.equals(lastName)) count++;
        }

        return count;

    }
}
