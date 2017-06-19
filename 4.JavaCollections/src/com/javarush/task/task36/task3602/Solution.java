package com.javarush.task.task36.task3602;

public class Solution {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() throws ClassNotFoundException {
        return Class.forName("java.util.Collections$EmptyList");
    }
}
