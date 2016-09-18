package com.javarush.test.level15.lesson12.home05;

/**
 * Created by Евгений on 10.08.2016.
 */
public class SubSolution extends Solution {

    protected SubSolution(String a){super(a);}
    protected SubSolution(Double a){super(a);}
    protected SubSolution(Integer a){super(a);}

    public SubSolution(int a, int b){super(a, b);}
    public SubSolution(double a, double b){super(a, b);}
    public SubSolution(Object pub_obj){super(pub_obj);}

    SubSolution(){super();}
    SubSolution(String a, int b){super(a, b);}
    SubSolution(Double a, double b){super(a, b);}

    private SubSolution(boolean a) {}
    private SubSolution(int a) {}
    private SubSolution(double a) {}

}
