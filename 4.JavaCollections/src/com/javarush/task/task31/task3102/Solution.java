package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File rootFolder = new File(root);
        if (rootFolder.isFile()) return new ArrayList<>();

        List<String> result = new ArrayList<>();

        Queue<File> folders = new LinkedList<>();
        folders.add(rootFolder);

        while (!folders.isEmpty()) {
            File[] files = folders.remove().listFiles();
            if (files == null || files.length == 0) break;

            for (File file : files) {
                if (file.isDirectory()) folders.add(file);
                else if (file.isFile()) result.add(file.getAbsolutePath());
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        List<String> list = getFileTree("C:\\test\\unzip");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
