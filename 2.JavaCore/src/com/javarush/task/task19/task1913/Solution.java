package com.javarush.task.task19.task1913;

/* 
Выводим только цифры
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream defOut = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(bytes);
        System.setOut(newOut);
        testString.printSomething();
        System.setOut(defOut);
        String fromArr = bytes.toString().replaceAll("[^1-9]", "");
        System.out.println(fromArr);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's 1 a 23 text 4 f5-6or7 tes8ting");
        }
    }
}
