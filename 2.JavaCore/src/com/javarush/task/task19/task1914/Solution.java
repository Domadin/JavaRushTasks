package com.javarush.task.task19.task1914;

import javax.script.ScriptException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws ScriptException {
        PrintStream defOut = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(bytes);
        System.setOut(newOut);
        testString.printSomething();
        System.setOut(defOut);
        String equation = bytes.toString().substring(0, bytes.size() - 2);
        String[] data = equation.trim().split(" ");
        int firArg = Integer.parseInt(data[0]);
        int secArg = Integer.parseInt(data[2]);
        String operator = data[1];
        int result = 0;
        switch (operator) {
            case "+":
                result = firArg + secArg;
                break;
            case "-":
                result = firArg - secArg;
                break;
            case "*":
                result = firArg * secArg;
                break;
        }
        System.out.println(equation + result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("675 - 12 = ");
        }
    }
}

/*
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws ScriptException {
        PrintStream defOut = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(bytes);
        System.setOut(newOut);
        testString.printSomething();
        System.setOut(defOut);
        String fromArr = bytes.toString().replaceAll("[^ -?]", "");
        String equation = fromArr.substring(0, fromArr.lastIndexOf("="));
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        System.out.print(fromArr + engine.eval(equation));
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

*/
