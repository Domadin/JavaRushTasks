package com.javarush.task.task32.task3202;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("C:\\test\\test.txt"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream inputStream) throws IOException {
        StringWriter writer = new StringWriter();

        if (inputStream == null) return writer;

        /*int read;
        while ((read = inputStream.read()) > 0) writer.write(read);*/

        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String s = new String(bytes);
        writer.write(s);

        return writer;
    }
}