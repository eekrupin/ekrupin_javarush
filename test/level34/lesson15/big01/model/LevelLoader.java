package com.javarush.test.level34.lesson15.big01.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Евгений on 07.10.2016.
 */
public class LevelLoader {

    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level){

        final int SELL_SIZE = GameObject.FIELD_SELL_SIZE;

        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        int WallMatrix[][] = new int[][]{
            {1,0,0,0,0,0,0,1,1,1},
            {1,0,3,0,0,2,0,0,0,1},
            {1,1,1,0,0,0,0,0,0,0},
            {0,0,1,0,0,0,0,1,0,0},
            {0,0,0,0,4,0,0,1,0,1},
            {0,0,1,0,0,0,0,0,0,1},
            {0,0,1,0,0,0,0,1,0,1},
            {0,0,1,0,0,0,0,0,0,0},
            {0,0,1,1,1,0,0,1,0,0},
            {0,0,0,2,0,0,0,0,0,0},
        };

        for (int y=0 ; y < WallMatrix.length; y++) {
            for (int x=0 ; x < WallMatrix[y].length; x++) {
                int value = WallMatrix[y][x];
                if (value==0) continue;
                int coordinateX = x*SELL_SIZE+SELL_SIZE/2;
                int coordinateY = y*SELL_SIZE+SELL_SIZE/2;
                switch (value){
                    case 1: walls.add(new Wall(coordinateX, coordinateY)); break;
                    case 2: boxes.add(new Box(coordinateX, coordinateY)); break;
                    case 3: homes.add(new Home(coordinateX, coordinateY)); break;
                    case 4: player = new Player(coordinateX, coordinateY); break;
                }
            }
        }
        if (player==null) return null;
        GameObjects gameObjects = new GameObjects(walls, boxes, homes, player);
        return gameObjects;
    }

}
