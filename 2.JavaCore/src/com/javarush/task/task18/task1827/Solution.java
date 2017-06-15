package com.javarush.task.task18.task1827;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
//C:\test\price.txt

public class Solution {
    public static void main(String[] args) throws Exception {
        if (args[0].equals("-c")) {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = consoleReader.readLine();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            List<String> readData = new ArrayList<>();
            //Нахождение последнего индекса
            while (reader.ready()) {
                readData.add(reader.readLine());
            }
            reader.close();
            String lastIndex = readData.get(readData.size() - 1);
            lastIndex = lastIndex.substring(0, 8);
            if (lastIndex.contains(" ")) {
                lastIndex = lastIndex.substring(0, lastIndex.indexOf(" "));
            }

            String id = String.valueOf((Integer.parseInt(lastIndex) + 1));

            id = formatter(id, 8);
            String productName = formatter(args[1], 30);
            String price = formatter(args[2], 8);
            String quantity = formatter(args[3], 4);
            readData.add(id + productName + price + quantity);

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String s : readData) {
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        }
    }
    public static String formatter(String source, int maxSize) {
        if (source.length() > maxSize) {
            source = source.substring(0, maxSize);
        } else if (source.length() < maxSize) {
            StringBuilder sourceBuilder = new StringBuilder(source);
            while (sourceBuilder.length() < maxSize) {
                sourceBuilder.append(" ");
            }
            source = sourceBuilder.toString();
        }
        return source;
    }
}
