package com.javarush.task.task26.task2609;

/* 
Распределение элементов по корзинам с собственным локом
*/
public class Solution {
    private static final int NUMBER_LOCKS = 12;
    private final Node[] buckets; //Кастомный класс
    private final Object[] locks;

    static class Node { //Узел
        public Node next; //След. узел
        public Object key;
        public Object value;
    }

    public Solution(int numberBuckets) {
        buckets = new Node[numberBuckets]; //Объявляется массив "Корзин" (сигнатура)
        locks = new Object[NUMBER_LOCKS]; //Объявляется массив "Замков" (12 шт)
        for (int i = 0; i < NUMBER_LOCKS; i++) {
            locks[i] = new Object();        //Массив замков заполняется новыми объектами
        }
    }

    private int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public Object get(Object key) {
        int hash = hash(key); //Вычисление значения hash (рандомного значения от 0 до кол-ва "Корзин" - 1
        synchronized (locks[hash % locks.length]) {
            for (Node m = buckets[hash]; m != null; m = m.next) {
                if (m.key.equals(key)) return m.value;
            }
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % locks.length]) {
                buckets[i] = null;
            }
        }
    }

    public static void main(String[] args) {

    }
}
