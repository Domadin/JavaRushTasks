package com.javarush.task.task19.task1926;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String file = console.readLine();
        console.close();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            StringBuilder data = new StringBuilder(reader.readLine());
            data = data.reverse();
            System.out.println(data);
        }
        reader.close();
    }
}
