package com.javarush.task.task18.task1825;

/* 
Собираем файл
*/

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
    static Set<String> fileNames = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        fileNames.add(fileName);
        FileOutputStream fileWriter = new FileOutputStream(fileName.substring(0, fileName.lastIndexOf('.')));
        while (true) {
            fileName = reader.readLine();
            if (!fileName.equals("end")) {
                fileNames.add(fileName);
            } else break;
        }
        FileInputStream fileReader;
        for (String nameFromSet : fileNames) {
            fileReader = new FileInputStream(nameFromSet);
            byte[] buffer = new byte[fileReader.available()];
            fileReader.read(buffer);
            fileWriter.write(buffer);
            fileReader.close();
        }
        fileWriter.close();
    }
}
