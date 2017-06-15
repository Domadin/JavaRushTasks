package com.javarush.task.task28.task2803;

import java.util.concurrent.ThreadLocalRandom;

public class Solution {
    public static int getRandomIntegerBetweenNumbers(int from, int to) {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        return tlr.nextInt(from, to);
    }

    public static double getRandomDouble() {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        return tlr.nextDouble();
    }

    public static long getRandomLongBetween0AndN(long n) {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        return tlr.nextLong(n);
    }

    public static void main(String[] args) {
        int i = getRandomIntegerBetweenNumbers(1, 10);
        System.out.println("int от 1 до 10: " + i);
        double d = getRandomDouble();
        System.out.println("double любой: " + d);
        long l = getRandomLongBetween0AndN(10);
        System.out.println("long от 0 до 10: " + l);
    }
}
