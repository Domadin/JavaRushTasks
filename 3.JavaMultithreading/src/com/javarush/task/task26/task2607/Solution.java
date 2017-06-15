package com.javarush.task.task26.task2607;

public class Solution {
    public static void main(String[] args) {
    }

    public static class IntegerHolder {
        private int value;

        public synchronized int get() {
            return value;
        }

        public synchronized void set(int value) {
            this.value = value;
        }
    }
}
