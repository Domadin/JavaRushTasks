package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Solution {

    public static void main(String[] args) throws IOException {
        Path path;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            path = Paths.get(reader.readLine());
        }
        if (!Files.isDirectory(path)) System.out.println(path.toString() + " - не папка");
        else {
            MyFileVisitor visitor = new MyFileVisitor();
            Files.walkFileTree(path, visitor);
            System.out.println("Всего папок - " + (visitor.getFolders() - 1));
            System.out.println("Всего файлов - " + visitor.getFiles());
            System.out.println("Общий размер - " + visitor.getSize());
        }
    }
}
