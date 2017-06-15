package com.javarush.task.task31.task3111;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName, partOfContent;
    private int minSize, maxSize;
    private List<Path> foundFiles = new ArrayList<>();

    public String getPartOfName() {
        return partOfName;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public String getPartOfContent() {
        return partOfContent;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean canAdd = true;
        if (minSize != 0)
            if (attrs.size() < minSize) canAdd = false;


        if (maxSize != 0 && canAdd)
            if (attrs.size() > maxSize) canAdd = false;


        if (partOfName != null && canAdd)
            if (!file.getFileName().toString().contains(partOfName)) canAdd = false;


        if (partOfContent != null && canAdd) {
            StringBuilder builder = new StringBuilder();
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            }
            if (!builder.toString().contains(partOfContent)) canAdd = false;
        }

        if (canAdd) foundFiles.add(file);

        return super.visitFile(file, attrs);
    }
}
