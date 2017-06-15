package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    public static void main(String[] args) {
    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);
        int median, center = array.length / 2;
        if (array.length % 2 == 0) {
            median = (array[center] + array[center - 1]) / 2;
        } else {
            median = array[center];
        }
        Comparator<Integer> compMedian = Comparator.comparingInt(o -> Math.abs(o - median));
        Arrays.sort(array,compMedian);
        return array;
    }
}
