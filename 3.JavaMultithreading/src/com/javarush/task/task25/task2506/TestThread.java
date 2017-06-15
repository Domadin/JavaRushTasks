package com.javarush.task.task25.task2506;

public class TestThread extends Thread {
    @Override
    public void run() {
        while (!Thread.interrupted()){
        }
    }
}
