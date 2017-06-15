package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        Map<String, Double> data = new TreeMap<>();
        String filename = args[0];
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (reader.ready()) {
            String[] input = reader.readLine().split(" ");
            String name = input[0];
            Double value = Double.valueOf(input[1]);
            if (data.containsKey(input[0])) {
                data.put(name, data.get(name) + value);
            } else {
                data.put(name, value);
            }
        }
        reader.close();
        double max = 0;
        Set<String> maxNames = new TreeSet<>();
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            double value = entry.getValue();
            if (value > max) {
                maxNames.clear();
                maxNames.add(entry.getKey());
                max = value;
            } else if (value == max) {
                maxNames.add(entry.getKey());
            }
        }
        for (String name : maxNames) {
            System.out.println(name);
        }
    }
}
