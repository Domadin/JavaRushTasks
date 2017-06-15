package com.javarush.task.task25.task2511;

import java.util.TimerTask;

/* 
Вооружаемся до зубов!
*/
public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        this.handler = (t, e) -> {
            String name = t.getName().replaceAll(".", "*");
            System.out.println(e.getMessage().replace(t.getName(), name));
            e.printStackTrace();
        };
    }

    @Override
    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread.currentThread().setName("Thread-0");
            System.out.println(Thread.currentThread().getName());
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    @Override
    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    @Override
    public boolean cancel() {
        return original.cancel();
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new TimerTask() {
            @Override
            public void run() {
                throw new IllegalArgumentException();
            }
        });
        solution.run();
    }
}