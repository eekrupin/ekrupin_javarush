package com.javarush.test.level37.lesson04.big01.female;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by Евгений on 11.10.2016.
 */
public class FemaleFactory implements AbstractFactory {
    public Human getPerson(int age){
        if (age<=14) return new KidGirl();
        else if (age<=19) return new TeenGirl();
        else return new Woman();
    }
}
