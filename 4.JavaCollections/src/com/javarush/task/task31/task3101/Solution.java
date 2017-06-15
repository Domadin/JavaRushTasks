package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        String allFilesContentPath = resultFileAbsolutePath.getParent() + "\\allFilesContent.txt";
        File allFilesContent = new File(allFilesContentPath);
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
        try (BufferedOutputStream fileOutput = new BufferedOutputStream(new FileOutputStream(allFilesContent))) {

        Set<File> files = findFiles(path);


            for (File file : files) {
                try (BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(file))) {
                    while (fileInput.available() > 0) {
                        fileOutput.write(fileInput.read());
                    }
                }
                fileOutput.write(System.getProperty("line.separator").getBytes());
            }
        }
    }

    public static Set<File> findFiles(final File folder) {
        Set<File> result = new TreeSet<>((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));

        File[] files = folder.listFiles();
        if (files == null) {
            return new TreeSet<>();
        }

        for (File file : files) {
            if (file.isFile()) {
                if (file.length() > 50) FileUtils.deleteFile(file);
                else {
                    result.add(file);
                }
            } else if (file.isDirectory()) {
                result.addAll(findFiles(file));
            }
        }
        return result;
    }
}
