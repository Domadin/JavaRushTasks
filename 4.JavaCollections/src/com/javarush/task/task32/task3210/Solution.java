package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;


public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        long number = Long.valueOf(args[1]);
        String text = args[2];

        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        int textLength = text.length();
        byte[] readData = new byte[textLength];

        raf.seek(number);
        raf.read(readData, 0, textLength);
        String readString = new String(readData);
        raf.seek(raf.length());

        if (readString.equals(text)) raf.write("true".getBytes());
        else raf.write("false".getBytes());
    }
}
