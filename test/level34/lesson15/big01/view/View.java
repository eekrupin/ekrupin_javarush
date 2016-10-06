package com.javarush.test.level34.lesson15.big01.view;

import com.javarush.test.level34.lesson15.big01.controller.Controller;

import javax.swing.*;


/**
 * Created by Мария on 06.10.2016.
 */
public class View extends JFrame {
    private Controller controller;

    public View(Controller controller){
        this.controller = controller;
    }
}
