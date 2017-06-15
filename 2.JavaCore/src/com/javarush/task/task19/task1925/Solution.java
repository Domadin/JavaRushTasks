package com.javarush.task.task19.task1925;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileRead = args[0];
        String fileWrite = args[1];
        BufferedReader reader = new BufferedReader(new FileReader(fileRead));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileWrite));
        while (reader.ready()) {
            String[] data = reader.readLine().split(" ");
            int last = data.length - 1;
            for (int i = 0; i < last; i++) {
                if (data[i].length() > 6) {
                    writer.write(data[i] + ",");
                }
            }
            if (data[last].length() > 6 && !reader.ready()) {
                writer.write(data[last]);
            } else {
                writer.write(data[last] + ",");
            }
        }
        reader.close();
        writer.close();
    }
}
/*
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileRead = args[0];
        String fileWrite = args[1];
        BufferedReader reader = new BufferedReader(new FileReader(fileRead));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileWrite));
        StringBuilder allData = new StringBuilder();
        while (reader.ready()) {
            String[] data = reader.readLine().split(" ");
            for (String aData : data) {
                if (aData.length() > 6) {
                    allData.append(aData).append(",");
                }
            }
        }
        writer.write(String.valueOf(allData).substring(0, allData.length() - 1));
        reader.close();
        writer.close();
    }
}
*/
