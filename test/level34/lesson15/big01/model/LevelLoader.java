package com.javarush.test.level34.lesson15.big01.model;

import java.io.*;
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

        Boolean beginLevel = false;
        String str;
        try (FileInputStream fileInputStream = new FileInputStream(levels.toFile());
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream))){
            moveToPositionMaze(reader, level);
            reader.readLine();
            int totalX = Integer.parseInt(reader.readLine().substring(8));
            int totalY = Integer.parseInt(reader.readLine().substring(8));
            reader.readLine();
            reader.readLine();
            reader.readLine();
            for (int y = 0; y < totalY ; y++) {
                str = reader.readLine();
                int lengthLine = str.length();
                for (int x = 0; x < lengthLine; x++){
                    int coordinateX = x*SELL_SIZE+SELL_SIZE/2;
                    int coordinateY = y*SELL_SIZE+SELL_SIZE/2;
                    switch (str.charAt(x)){
                        case 'X': walls.add(new Wall(coordinateX, coordinateY)); break;
                        case '*': boxes.add(new Box(coordinateX, coordinateY)); break;
                        case '.': homes.add(new Home(coordinateX, coordinateY)); break;
                        case '@': player = new Player(coordinateX, coordinateY); break;
                        case '&':
                            boxes.add(new Box(coordinateX, coordinateY));
                            homes.add(new Home(coordinateX, coordinateY));
                            break;
                    }
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (player==null) return null;
        GameObjects gameObjects = new GameObjects(walls, boxes, homes, player);
        return gameObjects;
    }

    private void moveToPositionMaze(BufferedReader reader, int level) throws Exception {
        level = level==60 ? 60 : level%60;
        final String lineMaze = "Maze:";
        String str;
        Boolean levelFound = false;
        while ((str=reader.readLine())!=null){
            if (str.contains(lineMaze) && Integer.parseInt(str.substring(6).trim())==level) {
                levelFound = true;
                break;
            }
        }
        if (!levelFound) throw new Exception("Level: " + level + " not found");
    }

}
