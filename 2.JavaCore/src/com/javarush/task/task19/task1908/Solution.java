package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String readFile = console.readLine();
        String writeFile = console.readLine();
        console.close();
        BufferedReader reader = new BufferedReader(new FileReader(readFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
        while (reader.ready()) {
            String[] data = reader.readLine().split(" ");
            for (String element : data) {
                try {
                    writer.write(Integer.parseInt(element) + " ");
                } catch (Exception e) {
                }
            }
        }
        reader.close();
        writer.close();
    }
}
