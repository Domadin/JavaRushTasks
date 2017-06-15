package com.javarush.task.task18.task1826;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(args[1]));
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(args[2]));

        byte[] input = new byte[reader.available()];
        while (reader.available() > 0) {
            reader.read(input);
        }
        reader.close();

        if (args[0].equals("-e")) {
            for (int i = 0; i < input.length; i++) {
                input[i] = ++input[i];
            }
        } else {
            for (int i = 0; i < input.length; i++) {
                input[i] = --input[i];
            }
        }
        writer.write(input);
        writer.close();
    }
}
