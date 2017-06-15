package com.javarush.task.task25.task2512;

public class TestClass implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("test");
    }
}
