package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
//C:\test\price.txt

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = consoleReader.readLine();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        //Заполняем лист данными из блокнота (строками)
        List<String> readData = new ArrayList<>();
        while (reader.ready()) {
            readData.add(reader.readLine());
        }
        reader.close();

        //Ищем совпадение id, переданного с аргументов с id в списке
        String id = args[1];
        int numberInData = -1;
        for (int i = 0; i < readData.size(); i++) {
            String s = readData.get(i);
            String indexOfData = s.substring(0, 8);
            if (indexOfData.contains(" ")) {
                indexOfData = indexOfData.substring(0, indexOfData.indexOf(" "));
            }
            if (id.equals(indexOfData)) {
                numberInData = i;
                break;
            }
        }


        if (args[0].equals("-u") && numberInData != -1) {
            id = formatter(id, 8);
            String productName = formatter(args[2], 30);
            String price = formatter(args[3], 8);
            String quantity = formatter(args[4], 4);
            readData.set(numberInData, id + productName + price + quantity);

        }
        if (args[0].equals("-d") && numberInData != -1) {
            readData.remove(numberInData);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (String st : readData) {
            writer.write(st);
            writer.newLine();
        }
        writer.close();
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
/*
import java.io.*;
import java.util.ArrayList;
import java.util.List;
//C:\test\price.txt

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = consoleReader.readLine();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        //Заполняем лист данными из блокнота (строками)
        List<String> readData = new ArrayList<>();
        while (reader.ready()) {
            readData.add(reader.readLine());
        }
        reader.close();

        //Ищем совпадение id, переданного с аргументов с id в списке
        String id = args[1];
        int numberInData = -1;
        String s = readData.get(0);
        String indexOfData = s.substring(0, 8);
        if (indexOfData.contains(" ")) {
            indexOfData = indexOfData.substring(0, indexOfData.indexOf(" "));
        }
        if (id.equals(indexOfData)) {
            numberInData = 0;
        } else {
            for (int i = 1; i < readData.size(); i++) {
                s = readData.get(i);
                indexOfData = s.substring(0, 8);
                if (indexOfData.contains(" ")) {
                    indexOfData = indexOfData.substring(0, indexOfData.indexOf(" "));
                }
                if (id.equals(indexOfData)) {
                    numberInData = i;
                    break;
                }
            }
        }

        if (args[0].equals("-u") && numberInData != -1) {
            id = formatter(id, 8);
            String productName = formatter(args[2], 30);
            String price = formatter(args[3], 8);
            String quantity = formatter(args[4], 4);
            readData.set(numberInData, id + productName + price + quantity);

        }
        if (args[0].equals("-d") && numberInData != -1) {
            readData.remove(numberInData);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (String st : readData) {
            writer.write(st);
            writer.newLine();
        }
        writer.close();
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
*/
