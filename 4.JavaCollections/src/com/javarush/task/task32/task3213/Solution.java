package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader == null) return "";

        StringBuilder builder = new StringBuilder();
        int readByte;
        while ((readByte = reader.read()) > 0) {
            char c = (char) (readByte + key);
            builder.append(c);
        }
        return builder.toString();
    }
}
