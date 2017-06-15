package com.javarush.task.task19.task1911;

/* 
Ридер обертка
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream defaultOut = System.out;
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(byteArray);
        System.setOut(newOut);
        testString.printSomething();
        String fromArray = byteArray.toString().toUpperCase();
        System.setOut(defaultOut);
        System.out.print(fromArray);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
