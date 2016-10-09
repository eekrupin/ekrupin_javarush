package com.javarush.test.level35.lesson08.bonus01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <K, V> Map<K, V> convert(List<? extends Convertable<K>> list) {
        Map<K, V> result = new HashMap();
        for (Convertable<K> t : list) {
            result.put(t.getKey(), (V)t);
        }

        return result;
    }
}
