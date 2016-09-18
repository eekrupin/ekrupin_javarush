package com.javarush.test.level25.lesson05.home01;

/**
 * Created by ekrupin on 07.09.2016.
 */
public class LoggingStateThread extends Thread {
    Thread target;

    public LoggingStateThread(Thread target) {
        this.target = target;
        setDaemon(true);
    }

    @Override
    public void run() {
        State state = target.getState();
        System.out.println(state);
        while (state!=State.TERMINATED){
            if (state!=target.getState()){
                state = target.getState();
                System.out.println(state);
            }
        }
    }
}
