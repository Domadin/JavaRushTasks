package com.javarush.task.task28.task2805;

public class MyThread extends Thread {
    public MyThread() {
        this.setPriority(priority());
    }

    public MyThread(Runnable target) {
        super(target);
        this.setPriority(priority());
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        this.setPriority(priority());
    }

    public MyThread(String name) {
        super(name);
        this.setPriority(priority());
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        this.setPriority(priority());
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        this.setPriority(priority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        this.setPriority(priority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        this.setPriority(priority());
    }

    private static int priority = 0;

    private int priority() {
        priority++;
        if (priority > Thread.MAX_PRIORITY) {
            priority = 1;
        }
        return priority;
    }
}
