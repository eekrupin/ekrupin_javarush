package com.javarush.test.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread{
    public static Integer priority = Thread.MIN_PRIORITY;

    public MyThread() {
        setNewPriority();
    }

    private void setNewPriority() {
        synchronized (priority){
            if (priority>Thread.MAX_PRIORITY) priority=Thread.MIN_PRIORITY;
            if (priority>getThreadGroup().getMaxPriority()) setPriority(getThreadGroup().getMaxPriority());
            else setPriority(priority);
            priority++;
        }
    }

    public MyThread(Runnable target) {
        super(target);
        setNewPriority();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setNewPriority();
    }

    public MyThread(String name) {
        super(name);
        setNewPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setNewPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setNewPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setNewPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setNewPriority();
    }
}
