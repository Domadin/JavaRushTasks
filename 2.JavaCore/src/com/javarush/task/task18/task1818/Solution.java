package com.javarush.task.task18.task1818;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileNameReader.readLine()));
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileNameReader.readLine()));
        while (inputStream.available() > 0) {
            outputStream.write(inputStream.read());
        }
        inputStream = new BufferedInputStream(new FileInputStream(fileNameReader.readLine()));
        while (inputStream.available() > 0) {
            outputStream.write(inputStream.read());
        }
        outputStream.close();
        inputStream.close();
    }
}
