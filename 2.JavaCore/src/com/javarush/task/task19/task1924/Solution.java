package com.javarush.task.task19.task1924;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Solution {
    public static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String file = console.readLine();
        console.close();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            String[] data = reader.readLine().split(" ");
            for (int i = 0; i < data.length; i++) {
                String dataS = data[i];
                try {
                    int parse = Integer.parseInt(dataS);
                    if (parse > 0 && parse < 13) {
                        data[i] = map.get(parse);
                    }
                } catch (NumberFormatException e) {
                }
            }
            StringBuilder dataAfter = new StringBuilder();
            for (String dataS : data) {
                dataAfter.append(dataS).append(" ");
            }
            System.out.println(dataAfter.toString().trim());
        }
        reader.close();
    }
}

