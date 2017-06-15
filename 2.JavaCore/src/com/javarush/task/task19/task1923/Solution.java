package com.javarush.task.task19.task1923;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileRead = args[0];
        String fileWrite = args[1];
        BufferedReader reader = new BufferedReader(new FileReader(fileRead));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileWrite));
        while (reader.ready()) {
            String[] data = reader.readLine().split(" ");
            for (String word : data) {
                if (word.matches(".\\d.+")) {
                    writer.write(word + " ");
                }
            }
        }
        reader.close();
        writer.close();
    }
}
