package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread target;

    public LoggingStateThread(Thread target) {
        this.target = target;
        setDaemon(true);
    }

    @Override
    public void run() {
        State state = target.getState();
        System.out.println(target.getState());
        while (state != State.TERMINATED) {
            if (state != target.getState()) {
                state = target.getState();
                System.out.println(target.getState());
            }
        }
/*        while (target.getState() != State.TERMINATED) {
            System.out.println(target.getState());
        }*/
    }
}
