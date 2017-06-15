package com.javarush.task.task18.task1824;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    private static Set<BufferedReader> readers = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String fileName = null;
            try {
                fileName = reader.readLine();
                readers.add(new BufferedReader(new FileReader(fileName)));
            } catch (FileNotFoundException e) {
                System.out.println(fileName);
                for (BufferedReader r : readers) {
                    r.close();
                }
                break;
            }
        }
    }
}
