package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread current;

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void start(String threadName) {
        current = new Thread(this, threadName);
        current.start();
    }

    @Override
    public void stop() {
        current.interrupt();
    }
}
