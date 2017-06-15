package com.javarush.task.task19.task1912;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream defaultOut = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream toArrOut = new PrintStream(bytes);
        System.setOut(toArrOut);
        testString.printSomething();
        System.setOut(defaultOut);
        String fromArr = bytes.toString().replace("te", "??");
        System.out.println(fromArr);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
