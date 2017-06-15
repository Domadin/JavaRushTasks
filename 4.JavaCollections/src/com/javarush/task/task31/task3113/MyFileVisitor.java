package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
    private int folders = 0, files = 0, size = 0;

    public int getFolders() {
        return folders;
    }

    public int getFiles() {
        return files;
    }

    public int getSize() {
        return size;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isDirectory(file)) folders++;
        if (Files.isRegularFile(file)) {
            files++;
            size += attrs.size();
        }
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        folders++;
        return super.postVisitDirectory(dir, exc);
    }
}
