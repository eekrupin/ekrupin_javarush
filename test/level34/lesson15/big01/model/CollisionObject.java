package com.javarush.test.level34.lesson15.big01.model;

/**
 * Created by Евгений on 06.10.2016.
 */
public abstract class CollisionObject extends GameObject{

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction){
        int diffX = 0;
        int diffY = 0;
        switch (direction){
            case UP:   diffY=-1; break;
            case DOWN: diffY= 1; break;
            case LEFT: diffX=-1; break;
            case RIGHT:diffX= 1; break;
        }
        boolean isCollision = (gameObject.getX()==(getX()+FIELD_SELL_SIZE*diffX)) &&
                (gameObject.getY()==(getY()+FIELD_SELL_SIZE*diffY));
        return isCollision;
    }

}
