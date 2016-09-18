package com.javarush.test.level08.lesson08.task05;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution
{
    public static void main(String[] args) {
        HashMap<String, String> map = createMap();
        removeTheFirstNameDuplicates(map);

        for (Map.Entry<String, String>pair:map.entrySet()) {
            System.out.println(pair.getKey() + " - " + pair.getValue());
        }
    }

    public static HashMap<String, String> createMap()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("Иванов1", "Сергей");
        map.put("Иванов2", "Петр1");
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

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        HashMap<String, Integer> names = new HashMap<>();
        for(String s: map.values()){
            int count = names.containsKey(s) ? 1+names.get(s):1;
            names.put(s, count);
        }

        for (Map.Entry<String, Integer>pair:names.entrySet()) {
            if(pair.getValue()>1) removeItemFromMapByValue(map, pair.getKey());
        }

    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }
}
