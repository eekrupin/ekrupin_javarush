package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Евгений on 07.10.2016.
 */
public class Wall extends CollisionObject {

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GRAY);
        graphics.fillRect(getX(), getY(), getWidth(), getWidth());
    }
}
