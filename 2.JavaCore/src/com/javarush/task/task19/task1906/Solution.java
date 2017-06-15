package com.javarush.task.task19.task1906;

/* 
Четные байты
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readFile = reader.readLine();
        String writeFile = reader.readLine();
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(readFile));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(writeFile));
        while (fileReader.ready()) {
            fileReader.read();
            fileWriter.write(fileReader.read());
        }
        fileReader.close();
        fileWriter.close();
    }
}
