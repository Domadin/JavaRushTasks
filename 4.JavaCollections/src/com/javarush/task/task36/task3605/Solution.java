package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(args[0]));

        Set<Character> charSet = new TreeSet<>();
        for (String line : lines) {
            for (Character ch : line.toCharArray()) {
                if (Character.isAlphabetic(ch)) {
                    charSet.add(Character.toLowerCase(ch));
                }
            }
        }
        int count = 0;
        for (Character ch : charSet) {
            System.out.print(ch);
            count++;
            if (count == 5) break;
        }
    }
}
