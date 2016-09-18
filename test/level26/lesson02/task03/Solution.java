package com.javarush.test.level26.lesson02.task03;

import java.lang.reflect.Array;
import java.util.*;

/* Убежденному убеждать других не трудно.
В таблице есть колонки, по которым можно сортировать.
Пользователь имеет возможность настроить под себя список колонок, которые будут сортироваться.
Напишите public static компаратор CustomizedComparator, который будет:
1. в конструкторе принимать список компараторов
2. сортировать данные в порядке, соответствующем последовательности компараторов.
Все переданные компараторы сортируют дженерик тип Т
В конструктор передается как минимум один компаратор
*/
public class Solution {

    public static class CustomizedComparator<T> implements Comparator<T>{
        ArrayList<Comparator<T>> list;
        public CustomizedComparator(Comparator<T>... list) {
            this.list = new ArrayList<>(list.length);
            Collections.addAll(this.list, list);
        }

        @Override
        public int compare(T o1, T o2) {
            int res = 0;
            if (o1.getClass() == o2.getClass()) {
                for (Comparator<T> tComparator : list) {
                    res = tComparator.compare(o1, o2);
                    if (res != 0) break;
                }
            }
            return res;
        }
    }

    /*public static void main(String[] args) {
        ArrayList<Comparable> list = new ArrayList<>();
        list.add(456);
        list.add(32);
        //list.add("zvcds");
        //list.add("adsfs");
        //list.add("sadasdsa");
        list.add(452D);

        Comparator c1 = new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        Comparator c2 = new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        Comparator c3 = new Comparator<Double>(){
            @Override
            public int compare(Double o1, Double o2) {
                return o1.compareTo(o2);
            }
        };

        Collections.sort(list, new CustomizedComparator<Comparable>(c1, c2, c3));

        System.out.println(list.toString());

    }*/

}
