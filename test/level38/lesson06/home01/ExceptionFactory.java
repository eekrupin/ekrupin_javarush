package com.javarush.test.level38.lesson06.home01;

public class ExceptionFactory {
    public static Throwable getException(Enum e){

        if (e!=null) {
            if (e instanceof ExceptionApplicationMessage) return new Exception(e.name().charAt(0) + e.name().substring(1).toLowerCase().replace("_", " "));
            else if (e instanceof ExceptionUserMessage) return new Error(e.name().charAt(0) + e.name().substring(1).toLowerCase().replace("_", " "));
            else if (e instanceof ExceptionDBMessage) return new RuntimeException(e.name().charAt(0) + e.name().substring(1).toLowerCase().replace("_", " "));
        }
        return new IllegalArgumentException();
    }

}
