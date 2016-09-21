package com.javarush.test.level30.lesson06.home01;

import java.util.concurrent.RecursiveTask;

/**
 * Created by ekrupin on 21.09.2016.
 */
public class BinaryRepresentationTask extends RecursiveTask<String>{
    private int i;

    public BinaryRepresentationTask (int i) {
        this.i = i;
    }

    @Override
    protected String compute() {
        return binaryRepresentationMethod(i);
    }

    private String binaryRepresentationMethod(int x) {
        int a = x % 2;
        int b = x / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            return binaryRepresentationMethod(b) + result;
        }
        return result;
    }

}
