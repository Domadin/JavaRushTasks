package com.javarush.task.task26.task2603;

import java.util.Comparator;

public class Solution {
    public static void main(String[] args) {

    }

    public static class CustomizedComparator<T> implements Comparator<T> {
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator... comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(T o1, T o2) {
            int compResult = 0;
            for (Comparator comparator : comparators) {
                compResult = comparator.compare(o1, o2);
                if (compResult != 0) {
                    break;
                }
            }
            return compResult;
        }
    }
}