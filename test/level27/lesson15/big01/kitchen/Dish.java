package com.javarush.test.level27.lesson15.big01.kitchen;

public enum Dish {
    Fish(25), Steak(30), Soup(15), Juice(5), Water(3);

    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }

    public static String allDishesToString(){
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (Dish dish : values()) {
            sb.append(prefix);
            sb.append(dish);
            if (prefix.isEmpty()) prefix = ", ";
        }
        return sb.toString();
    }

    public int getDuration() {
        return duration;
    }
}
