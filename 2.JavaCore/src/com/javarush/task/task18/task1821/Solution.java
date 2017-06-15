package com.javarush.task.task18.task1821;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        int[] ascii = new int[127];
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready()) {
            ascii[reader.read()] += 1;
        }
        for (int i = 0; i < ascii.length; i++) {
            if (ascii[i] > 0) {
                System.out.println((char) i + " " + ascii[i]);
            }
        }
        reader.close();
    }
}

/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        Map<Character, Integer> values = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready()) {
            char c = (char) reader.read();
            if (values.containsKey(c)) {
                values.put(c, values.get(c) + 1);
            } else {
                values.put(c, 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : values.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        reader.close();
    }
}*/
