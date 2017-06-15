package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Solution {
    public static List<String> words = new ArrayList<>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String file = console.readLine();
        console.close();
        BufferedReader fileRead = new BufferedReader(new FileReader(file));
        while (fileRead.ready()) {
            String data = fileRead.readLine();
            int count = 0;
            for (String word : words) {
                if (data.contains(word)){
                    count++;
                }
            }
            if (count == 2) {
                System.out.println(data);
            }
        }
        fileRead.close();
    }
}
