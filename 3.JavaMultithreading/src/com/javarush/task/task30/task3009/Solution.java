package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> result = new HashSet<>();
        for (int radix = Character.MIN_RADIX; radix <= Character.MAX_RADIX; radix++) {
            try {
                StringBuilder builder = new StringBuilder(Integer.toString(Integer.valueOf(number), radix));
                String temp = builder.toString();
                if (temp.equals(builder.reverse().toString())) {
                    result.add(radix);
                }
            } catch (NumberFormatException e) {
            }
        }
        return result;
    }
}