package com.javarush.task.task18.task1817;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class Solution {
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filename));
        int spaceCount = 0;
        int totalChars = inputStream.available();
        while (inputStream.available() > 0) {
            int c = inputStream.read();
            if (c == 32) {
                spaceCount++;
            }
        }
        float ratio = (float) spaceCount / totalChars * 100;
        System.out.println(String.format("%.2f",ratio));
        inputStream.close();
    }
}

