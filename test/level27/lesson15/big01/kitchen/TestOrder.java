package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 17.09.2016.
 */
public class TestOrder extends Order {

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        List<Dish> list = new ArrayList<>();
        //int count = (int)(Math.random()*4);
        int count = 2;

        int maxCount = Dish.values().length;
        for (int i = 0; i <=count ; i++) {
            int indDish = (int)(Math.random()*maxCount);
            list.add(Dish.values()[indDish]);
        }
        dishes = list;
    }
}
