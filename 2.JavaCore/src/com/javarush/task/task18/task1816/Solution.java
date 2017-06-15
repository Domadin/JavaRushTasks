package com.javarush.task.task18.task1816;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filename));
        int count = 0;
        while (inputStream.available() > 0) {
            int c = inputStream.read();
            if ((c > 64 && c <= 91) || (c >= 96 && c <= 123)) {
                count++;
            }
        }
        System.out.println(count);
        inputStream.close();
    }
}
