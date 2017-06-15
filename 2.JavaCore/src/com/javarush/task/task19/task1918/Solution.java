package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        String tag = args[0];
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(consoleReader.readLine()));
        consoleReader.close();

        StringBuilder fileDataSB = new StringBuilder();
        while (fileReader.ready()) {
            fileDataSB.append(fileReader.readLine());
        }
        fileReader.close();

        String openTag = "<" + tag;
        String closeTag = "</" + tag + ">";
        List<Integer> openList = getIndexes(fileDataSB, openTag);
        List<Integer> closeList = getIndexes(fileDataSB, closeTag);

        for (int i = 0; i < openList.size(); ) {
            int openIndex = openList.get(0);
            int closeIndex = closeList.get(0);
            int innerTagsCount = 0;

            for (int op = 1; op < openList.size(); op++) {
                if (openList.get(op) < closeIndex) {
                    innerTagsCount++;
                } else break;
            }

            if (innerTagsCount > 0) {
                closeIndex = closeList.get(innerTagsCount);
            }

            System.out.println(fileDataSB.substring(openIndex, closeIndex + closeTag.length()));

            openList.remove(0);
            closeList.remove(innerTagsCount);
        }
    }

    public static List<Integer> getIndexes(StringBuilder source, String sub) {
        List<Integer> result = new ArrayList<>();
        for (int index = source.indexOf(sub); index >= 0; index = source.indexOf(sub, index + 1)) {
            result.add(index);
        }
        return result;
    }
}
