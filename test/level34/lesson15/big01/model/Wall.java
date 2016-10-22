package com.javarush.test.level34.lesson15.big01.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Евгений on 07.10.2016.
 */
public class Wall extends CollisionObject {

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        try {
            BufferedImage image = ImageIO.read(new File("src\\com\\javarush\\test\\level34\\lesson15\\big01\\res\\wall.png"));
            graphics.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
