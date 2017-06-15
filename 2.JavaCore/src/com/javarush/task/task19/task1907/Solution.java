package com.javarush.task.task19.task1907;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readFile = reader.readLine();
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(readFile));
        int count = 0;
        while (fileReader.ready()) {
            String temp = fileReader.readLine();
            String[] data = temp.split("[^a-zA-Z]");
            for (String st : data) {
                if (st.equals("world")) {
                    count++;
                }
            }
        }
        System.out.println(count);
        fileReader.close();
    }
}