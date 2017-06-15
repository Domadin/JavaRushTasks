package com.javarush.task.task30.task3013;

/* 
Набираем код
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int number = 87542;
        int number1 = 10245;
        System.out.println(fixedLengthString(Integer.toBinaryString(number)));
        System.out.println(fixedLengthString(Integer.toBinaryString(number1)));
        System.out.println(fixedLengthString(Integer.toBinaryString(number ^ number1)));
        System.out.println(fixedLengthString(Integer.toBinaryString(number1 ^ (number ^ number1))));
/*
        for (int i = 1; i < 32; i *= 2) {
            System.out.println("ЭТАП " + i);
            System.out.println(fixedLengthString(Integer.toBinaryString(number)) + " number");
            System.out.println(fixedLengthString(Integer.toBinaryString(~number)) + " ~number");
            System.out.println(fixedLengthString(Integer.toBinaryString(~number >> i)) + " ~number>>" + i);
            number &= ~number >> i;
        }
        System.out.println(fixedLengthString(Integer.toBinaryString(number)) + " number");*/

    }

    public static String fixedLengthString(String string) {
        return String.format("%1$" + 32 + "s", string).replace(" ", "0");
    }

    public int resetLowerBits(int number) {
        number = number & ~number >> 1;
        number = number & ~number >> 2;
        number = number & ~number >> 3;
        number = number & ~number >> 4;
        number = number & ~number >> 5;
        number = number & ~number >> 6;
        number = number & ~number >> 7;
        number = number & ~number >> 8;
        number = number & ~number >> 9;
        number = number & ~number >> 10;
        number = number & ~number >> 11;
        number = number & ~number >> 12;
        number = number & ~number >> 13;
        number = number & ~number >> 14;
        number = number & ~number >> 15;
        number = number & ~number >> 16;
        number = number & ~number >> 17;
        number = number & ~number >> 18;
        number = number & ~number >> 19;
        number = number & ~number >> 20;
        number = number & ~number >> 21;
        number = number & ~number >> 22;
        number = number & ~number >> 23;
        number = number & ~number >> 24;
        number = number & ~number >> 25;
        number = number & ~number >> 26;
        number = number & ~number >> 27;
        number = number & ~number >> 28;
        number = number & ~number >> 29;
        number = number & ~number >> 30;
        number = number & ~number >> 31;
        return number;
    }
}