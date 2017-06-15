package com.javarush.task.task18.task1819;


import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String firstFile = fileNameReader.readLine();
        String secondName = fileNameReader.readLine();
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(firstFile));
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(firstFile));
        inputStream = new BufferedInputStream(new FileInputStream(secondName));
        while (inputStream.available() > 0) {
            int i = inputStream.read();
            outputStream.write(i);
        }
        outputStream.write(bytes);
        inputStream.close();
        outputStream.close();
    }
}
