package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Solution {
    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        String archivePath = args[1];

        Map<String, byte[]> map = new LinkedHashMap<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(archivePath))) {        //Создает входящий поток для файла архива
            ZipEntry inputZipEntry;
            while ((inputZipEntry = zipInputStream.getNextEntry()) != null) {
                String key = inputZipEntry.getName();
                byte[] bytes = new byte[0];
                if (!inputZipEntry.isDirectory()) {
                    bytes = new byte[(int) inputZipEntry.getSize()];
                    zipInputStream.read(bytes);
                }
                map.put(key, bytes);
            }
        }

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(archivePath))) {
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
            String newFilePath = "new/" + fileName;

            boolean duplicateFound = false;
            for (Map.Entry<String, byte[]> pair : map.entrySet()) {
                String entryPath = pair.getKey();
                ZipEntry outputZipEntry = new ZipEntry(entryPath);
                zipOutputStream.putNextEntry(outputZipEntry);
                if (!outputZipEntry.isDirectory()) {
                    if (entryPath.endsWith(fileName)) {
                        Files.copy(Paths.get(filePath), zipOutputStream);
                        duplicateFound = true;
                    } else {
                        byte[] bytes = pair.getValue();
                        zipOutputStream.write(bytes);
                    }
                }
                zipOutputStream.closeEntry();
            }
            if (!duplicateFound) {
                ZipEntry outputZipEntry = new ZipEntry(newFilePath);
                zipOutputStream.putNextEntry(outputZipEntry);
                Files.copy(Paths.get(filePath), zipOutputStream);
                zipOutputStream.closeEntry();
            }
        }
    }
}
