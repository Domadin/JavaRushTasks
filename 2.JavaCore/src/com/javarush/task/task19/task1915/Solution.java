package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String file = console.readLine();
        console.close();
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
        PrintStream defOut = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(bytes);
        System.setOut(newOut);
        testString.printSomething();
        System.setOut(defOut);
        writer.write(bytes.toByteArray());
        writer.close();
        System.out.println(bytes.toString());
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

