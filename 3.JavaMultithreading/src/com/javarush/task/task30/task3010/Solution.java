package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        try {
            String number = args[0];
            int radixMin = 0;
            for (int radix = Character.MAX_RADIX; radix >= Character.MIN_RADIX; radix--) {
                try {
                    BigInteger bigInteger = new BigInteger(number, radix);
                    radixMin = radix;
                } catch (NumberFormatException e) {
                }
            }
            switch (radixMin) {
                case 0:
                    System.out.println("incorrect");
                    break;
                default:
                    System.out.println(radixMin);
            }
        } catch (Exception e) {
        }
    }
}