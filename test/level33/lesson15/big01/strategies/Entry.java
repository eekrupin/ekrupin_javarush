package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.Serializable;

/**
 * Created by Евгений on 05.10.2016.
 */
public class Entry implements Serializable {
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next){
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Long getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }

    public int hashCode(){
        return hash;
    }

    public String toString(){
        return getKey() + "=" + getValue();
    }

}
