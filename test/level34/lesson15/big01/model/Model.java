package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;

/**
 * Created by Мария on 06.10.2016.
 */
public class Model {
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("..\\res\\levels.txt"));

    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects(){
        return gameObjects;
    }

    public void restartLevel(int level){
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart(){
        restartLevel(currentLevel);
    }

    public void startNextLevel(){
        currentLevel++;
        restart();
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)) return;
        if (checkBoxCollision(direction)) return;
        switch (direction){
            case LEFT: player.move(-GameObject.FIELD_SELL_SIZE, 0); break;
            case RIGHT: player.move(GameObject.FIELD_SELL_SIZE, 0); break;
            case DOWN: player.move(0, GameObject.FIELD_SELL_SIZE); break;
            case UP: player.move(0, -GameObject.FIELD_SELL_SIZE); break;
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) return true;
        }
        return false;
    }
    public boolean checkBoxCollision(Direction direction){
        Player player = gameObjects.getPlayer();
        Box stopBox = null;
        for (Box box : gameObjects.getBoxes()) {
            if (player.isCollision(box, direction)) {
                stopBox = box;
            }
        }

        if (stopBox==null) return false;
        if (checkWallCollision(stopBox, direction)) return true;

        for (Box otherBox : gameObjects.getBoxes()) {
            if (otherBox == stopBox) continue;
            if (stopBox.isCollision(otherBox, direction)) return true;
        }

        switch (direction){
            case LEFT: stopBox.move(-GameObject.FIELD_SELL_SIZE, 0); break;
            case RIGHT: stopBox.move(GameObject.FIELD_SELL_SIZE, 0); break;
            case DOWN: stopBox.move(0, GameObject.FIELD_SELL_SIZE); break;
            case UP: stopBox.move(0, -GameObject.FIELD_SELL_SIZE); break;
        }
        return false;
    }

    public void checkCompletion(){//TODO есть проблема
        int boxInHome = 0;
        for (Box box : gameObjects.getBoxes()) {
            for (Home home : gameObjects.getHomes()) {
                if ((box.getX()==home.getX()) && (box.getY()==home.getY())){
                    boxInHome++;
                    break;
                }
            }
        }
        if (boxInHome==gameObjects.getBoxes().size()){
            eventListener.levelCompleted(currentLevel);
        }
    }

}
