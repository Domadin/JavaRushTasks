package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class Solution {
    public static volatile Map<String, Character> resultMap = new TreeMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String fileName = reader.readLine();
            if (!fileName.equals("exit")) {
                new ReadThread(fileName).start();
            } else break;
        }
        for (Map.Entry<String, Character> entry : resultMap.entrySet()) {
            System.out.println("Имя файла: "+entry.getKey() + ". Наибольшее число повторений у символа: \"" + entry.getValue()+"\"");
        }
    }

    public static class ReadThread extends Thread {
        BufferedReader reader;
        String fileName;
        HashMap<Integer, Integer> bytes;

        public ReadThread(String fileName) throws FileNotFoundException {
            reader = new BufferedReader(new FileReader(fileName));
            this.fileName = fileName;
        }

        @Override
        public void run() {
            try {
                fillBytesMap();
                findMaxByte();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void fillBytesMap() throws IOException {
            bytes = new HashMap<>();
            while (reader.ready()) {
                int i = reader.read();
                if (bytes.containsKey(i)) {
                    bytes.put(i, bytes.get(i) + 1);
                } else {
                    bytes.put(i, 1);
                }
            }
        }

        public void findMaxByte() {
            int max = 0;
            int byteMaX = 0;
            for (Map.Entry<Integer, Integer> entry : bytes.entrySet()) {
                int value = entry.getValue();
                if (max < value) {
                    max = value;
                    byteMaX = entry.getKey();
                }
            }
            System.out.println("В файле " + fileName + " наибольшее количество раз встречается символ \"" + (char) byteMaX + "\". Количество повторений - " + max);
            resultMap.put(fileName, (char) byteMaX);
        }
    }
}

/*
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class Solution {
    public static volatile Map<String, Integer> resultMap = new HashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String fileName = reader.readLine();
            if (!fileName.equals("exit")) {
                new ReadThread(fileName).start();
            } else break;
        }
    }

    public static class ReadThread extends Thread {
        BufferedReader reader;
        String fileName;
        HashMap<Integer, Integer> bytes;

        public ReadThread(String fileName) throws FileNotFoundException {
            reader = new BufferedReader(new FileReader(fileName));
            this.fileName = fileName;
        }

        @Override
        public void run() {
            try {
                bytes = new HashMap<>();
                while (reader.ready()) {
                    int i = reader.read();
                    if (bytes.containsKey(i)) {
                        bytes.put(i, bytes.get(i) + 1);
                    } else {
                        bytes.put(i, 1);
                    }
                }
                int max = 0;
                int byteMaX = 0;
                for (Map.Entry<Integer, Integer> entry : bytes.entrySet()) {
                    int value = entry.getValue();
                    if (max < value) {
                        max = value;
                        byteMaX = entry.getKey();
                    }
                }
                resultMap.put(fileName, byteMaX);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
*/
