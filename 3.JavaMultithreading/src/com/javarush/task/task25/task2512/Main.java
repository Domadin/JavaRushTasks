package com.javarush.task.task25.task2512;

public class Main {
    public Main() {
        Thread.setDefaultUncaughtExceptionHandler(new TestClass());
    }

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new TestClass());
        throw new RuntimeException("DEF", new IllegalAccessException("GHI"));
    }
}
