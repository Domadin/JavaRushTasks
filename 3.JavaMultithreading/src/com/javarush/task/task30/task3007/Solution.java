package com.javarush.task.task30.task3007;

/* 
Найдем число 2 в максимальной степени
*/
public class Solution {
    public static void main(String[] args) {

        System.out.println(maxPowerOf2(140_000));   //131072
        System.out.println(maxPowerOf2(1026));      //1024
        System.out.println(maxPowerOf2(17));        //16
    }

    public static String fixedLengthString(String string) {
        return String.format("%1$" + 32 + "s", string).replace(" ", "0");
    }

    public static int maxPowerOf2(int x) {
        x = x & ~x >> 1;
        x = x & ~x >> 2;
        x = x & ~x >> 3;
        x = x & ~x >> 4;
        x = x & ~x >> 5;
        x = x & ~x >> 6;
        x = x & ~x >> 7;
        x = x & ~x >> 8;
        x = x & ~x >> 9;
        x = x & ~x >> 10;
        x = x & ~x >> 11;
        x = x & ~x >> 12;
        x = x & ~x >> 13;
        x = x & ~x >> 14;
        x = x & ~x >> 15;
        x = x & ~x >> 16;
        x = x & ~x >> 17;
        x = x & ~x >> 18;
        x = x & ~x >> 19;
        x = x & ~x >> 20;
        x = x & ~x >> 21;
        x = x & ~x >> 22;
        x = x & ~x >> 23;
        x = x & ~x >> 24;
        x = x & ~x >> 25;
        x = x & ~x >> 26;
        x = x & ~x >> 27;
        x = x & ~x >> 28;
        x = x & ~x >> 29;
        x = x & ~x >> 30;
        x = x & ~x >> 31;
        return x;
    }
}