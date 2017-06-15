package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String readFile = console.readLine();
        String writeFile = console.readLine();
        console.close();
        BufferedReader reader = new BufferedReader(new FileReader(readFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
        while (reader.ready()) {
            writer.write(reader.readLine().replace(".", "!"));
        }
        reader.close();
        writer.close();
    }
}
