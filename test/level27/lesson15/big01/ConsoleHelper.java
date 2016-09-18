package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> list = new ArrayList<>();
        writeMessage(Dish.allDishesToString());
        if (Dish.values().length == 0) return list;
        while (true) {
            Dish dish;
            String str = readString();
            if (str.toUpperCase().equals("EXIT")) break;
            try {dish = Dish.valueOf(str);}
            catch (IllegalArgumentException e){
                ConsoleHelper.writeMessage(str +  " is not detected");
                continue;
            }
            list.add(dish);
        }
        return list;
    }


}
