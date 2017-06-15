package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream defOut = System.out;
        ByteArrayOutputStream arrayStream = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(arrayStream);
        System.setOut(newOut);
        testString.printSomething();
        System.setOut(defOut);
        String[] output = String.valueOf(arrayStream).split("\n");
        boolean even = false;
        for (String s : output) {
            if (even) {
                System.out.println(s);
                System.out.println("JavaRush - курсы Java онлайн");
                even = false;
            } else {
                System.out.println(s);
                even = true;
            }
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
